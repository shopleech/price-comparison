package shopleech;

import software.amazon.awscdk.CfnOutput;
import software.amazon.awscdk.Duration;
import software.amazon.awscdk.RemovalPolicy;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.apigateway.*;
import software.amazon.awscdk.services.certificatemanager.DnsValidatedCertificate;
import software.amazon.awscdk.services.certificatemanager.ICertificate;
import software.amazon.awscdk.services.cloudfront.*;
import software.amazon.awscdk.services.dynamodb.Attribute;
import software.amazon.awscdk.services.dynamodb.AttributeType;
import software.amazon.awscdk.services.dynamodb.Table;
import software.amazon.awscdk.services.dynamodb.TableProps;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.FunctionProps;
import software.amazon.awscdk.services.lambda.Runtime;
import software.amazon.awscdk.services.route53.*;
import software.amazon.awscdk.services.route53.targets.ApiGateway;
import software.amazon.awscdk.services.route53.targets.CloudFrontTarget;
import software.amazon.awscdk.services.s3.Bucket;
import software.amazon.awscdk.services.s3.deployment.BucketDeployment;
import software.amazon.awscdk.services.s3.deployment.ISource;
import software.amazon.awscdk.services.s3.deployment.Source;
import software.constructs.Construct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CdkStack extends Stack {
    public CdkStack(final Construct parent, final String id) {
        this(parent, id, null);
    }

    public CdkStack(final Construct parent, final String id, final StackProps props) {
        super(parent, id, props);

        // defines route53
        final IHostedZone zone =
                HostedZone.fromLookup(
                        this,
                        "Zone",
                        HostedZoneProviderProps.builder()
                                .domainName("shopleech.com")
                                .build());

        // defines dynamodb
        Attribute partitionKey = Attribute.builder()
                .name("productId")
                .type(AttributeType.STRING)
                .build();
        TableProps tableProps = TableProps.builder()
                .tableName("sl-products")
                .partitionKey(partitionKey)
                .removalPolicy(RemovalPolicy.RETAIN)
                .build();
        Table dynamodbTable = new Table(this, "sl-products", tableProps);

        Map<String, String> lambdaEnvMap = new HashMap<>();
        lambdaEnvMap.put("TABLE_NAME", dynamodbTable.getTableName());
        lambdaEnvMap.put("PRIMARY_KEY", "productId");

        // Defines a new lambda resource
        Function productApiFunction = new Function(this, "productApiFunction",
                FunctionProps.builder()
                        .functionName("sl-product-api")
                        .code(Code.fromAsset("./assets/product-api-0.1.0.jar"))
                        .handler("hello.handler")
                        .runtime(Runtime.JAVA_11)
                        .environment(lambdaEnvMap)
                        .timeout(Duration.seconds(30))
                        .memorySize(512)
                        .build()
        );

        dynamodbTable.grantReadWriteData(productApiFunction);

        // api gateway cert
        final ICertificate cert =
                DnsValidatedCertificate.Builder.create(this, "ApiCertificate")
                        .domainName("gateway.shopleech.com")
                        .hostedZone(zone)
                        .build();

        // defines a new api gateway for product-api
        RestApi api = new RestApi(this, "productApi",
                RestApiProps.builder().restApiName("Shopleech Product API")
                        .domainName(DomainNameOptions.builder()
                                .domainName("gateway.shopleech.com")
                                .certificate(cert)
                                .securityPolicy(SecurityPolicy.TLS_1_2)
                                .build())
                        .build());

        // set endpoint
        IResource items = api.getRoot().addResource("products");
        Integration getAllIntegration = new LambdaIntegration(productApiFunction);
        items.addMethod("GET", getAllIntegration);

        // set endpoint
        IResource singleItem = items.addResource("{id}");
        Integration getOneIntegration = new LambdaIntegration(productApiFunction);
        singleItem.addMethod("GET", getOneIntegration);

        // api alias record
        ARecord.Builder.create(this, "ApiAliasRecord")
                .recordName("gateway.shopleech.com")
                .target(RecordTarget.fromAlias(new ApiGateway(api)))
                .zone(zone)
                .build();

        // website
        List<String> siteDomainList = new ArrayList<>(1);
        siteDomainList.add("shopleech.com");

        // defines certificate
        final ICertificate certificate =
                DnsValidatedCertificate.Builder.create(this, "SiteCertificate")
                        .domainName("shopleech.com")
                        .hostedZone(zone)
                        .region("us-east-1") // cloudfront
                        .build();

        // defines s3
        Bucket siteBucket =
                Bucket.Builder.create(this, "SiteBucket")
                        .bucketName("shopleech-web")
                        .websiteIndexDocument("index.html")
                        .websiteErrorDocument("error.html")
                        .publicReadAccess(false)
                        .removalPolicy(RemovalPolicy.RETAIN)
                        .build();

        // Defines a cloudfront
        OriginAccessIdentity originAccessIdentity = OriginAccessIdentity.Builder
                .create(this, "OriginAccessIdentity")
                .comment("Allows Read-Access from CloudFront")
                .build();

        siteBucket.grantRead(originAccessIdentity);

        // cloudfront behaviours
        List<String> forwardedHeaders = new ArrayList<>(1);
        forwardedHeaders.add("Accept-Encoding");
        forwardedHeaders.add("Authorization");
        forwardedHeaders.add("Accept");
        forwardedHeaders.add("X-Origin-Verify");
        forwardedHeaders.add("Host");

        List<Behavior> behavioursList = new ArrayList<>(1);
        behavioursList.add(Behavior.builder().isDefaultBehavior(true).compress(true).build());

        List<Behavior> apiBehavioursList = new ArrayList<>(1);
        apiBehavioursList.add(
                Behavior.builder()
                        .pathPattern("/api/*")
                        .allowedMethods(CloudFrontAllowedMethods.ALL)
                        .forwardedValues(CfnDistribution.ForwardedValuesProperty.builder()
                                .queryString(true)
                                .headers(forwardedHeaders).build())
                        .compress(true)
                        .build());

        // source
        List<SourceConfiguration> sourceConfigurationsList = new ArrayList<>(1);
        sourceConfigurationsList.add(
                SourceConfiguration.builder()
                        .customOriginSource(
                                CustomOriginConfig.builder().domainName("gateway.shopleech.com")
                                        .originPath("/prod")
                                        .build())
                        .behaviors(apiBehavioursList)
                        .build());
        sourceConfigurationsList.add(
                SourceConfiguration.builder()
                        .s3OriginSource(
                                S3OriginConfig.builder()
                                        .s3BucketSource(siteBucket)
                                        .originAccessIdentity(originAccessIdentity)
                                        .build())
                        .behaviors(behavioursList)
                        .build());

        CloudFrontWebDistribution distribution =
                CloudFrontWebDistribution.Builder.create(this, "SiteDistribution")
                        .viewerCertificate(ViewerCertificate.fromAcmCertificate(certificate, ViewerCertificateOptions
                                .builder()
                                .aliases(siteDomainList)
                                .sslMethod(SSLMethod.SNI)
                                .securityPolicy(SecurityPolicyProtocol.TLS_V1_1_2016)
                                .build()
                        ))
                        .originConfigs(sourceConfigurationsList)
                        .build();

        ARecord.Builder.create(this, "SiteAliasRecord")
                .recordName("shopleech.com")
                .target(RecordTarget.fromAlias(new CloudFrontTarget(distribution)))
                .zone(zone)
                .build();

        // Deploy site contents to S3 bucket
        List<ISource> sources = new ArrayList<>(1);
        sources.add(Source.asset("./site-contents"));

        BucketDeployment.Builder.create(this, "DeployWithInvalidation")
                .sources(sources)
                .destinationBucket(siteBucket)
                .distribution(distribution)
                .build();

        CfnOutput.Builder.create(this, "DistributionId")
                .description("CloudFront Distribution Id")
                .value(distribution.getDistributionId())
                .build();
    }
}
