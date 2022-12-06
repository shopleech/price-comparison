package shopleech;

import software.amazon.awscdk.CfnOutput;
import software.amazon.awscdk.Duration;
import software.amazon.awscdk.RemovalPolicy;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.certificatemanager.DnsValidatedCertificate;
import software.amazon.awscdk.services.certificatemanager.ICertificate;
import software.amazon.awscdk.services.cloudfront.*;
import software.amazon.awscdk.services.dynamodb.*;
import software.amazon.awscdk.services.ecr.IRepository;
import software.amazon.awscdk.services.ecr.Repository;
import software.amazon.awscdk.services.lambda.*;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.route53.*;
import software.amazon.awscdk.services.route53.targets.CloudFrontTarget;
import software.amazon.awscdk.services.s3.Bucket;
import software.amazon.awscdk.services.s3.deployment.BucketDeployment;
import software.amazon.awscdk.services.s3.deployment.ISource;
import software.amazon.awscdk.services.s3.deployment.Source;
import software.constructs.Construct;

import java.util.*;

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

        ITable dynamodbTable2 = (ITable) Table.fromTableName(this, "sl-user-ddyn", "sl-user");

        Map<String, String> lambdaEnvMap = new HashMap<>();
        lambdaEnvMap.put("TABLE_NAME", dynamodbTable2.getTableName());
        lambdaEnvMap.put("PRIMARY_KEY", "id");

        IRepository repository = (IRepository) Repository.fromRepositoryName(this, "sl-iser-dynn-rep", "sl-user-api-1");

        // Defines a new lambda resource
        Function userApiFunction = new DockerImageFunction(this, "userApiFunction",
                DockerImageFunctionProps.builder()
                        .functionName("sl-user-api-1")
                        .description("")
                        .code(DockerImageCode.fromEcr(repository))
                        .environment(lambdaEnvMap)
                        .timeout(Duration.minutes(2))
                        .memorySize(512)
                        .build()
        );

        dynamodbTable2.grantReadWriteData(userApiFunction);

//        // defines a new api gateway for product-api
//        RestApi api = new RestApi(this, "ProductApi",
//                RestApiProps.builder()
//                        .restApiName("Shopleech Product API")
//                        .deploy(true)
//                        .build());
//
//        // set endpoint
//        IResource items = api.getRoot().addResource("api").addResource("products");;
//        Integration getAllIntegration = new LambdaIntegration(userApiFunction);
//        items.addMethod("GET", getAllIntegration);
//
//        // set endpoint
//        IResource singleItem = items.addResource("test").addResource("{id}");
//        Integration getOneIntegration = new LambdaIntegration(userApiFunction);
//        singleItem.addMethod("GET", getOneIntegration);

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
        List<Behavior> s3BehavioursList = new ArrayList<>(1);
        s3BehavioursList.add(
                Behavior.builder()
                        .isDefaultBehavior(true).build());

        List<Behavior> apiBehavioursList = new ArrayList<>(1);
        apiBehavioursList.add(
                Behavior.builder()
                        .pathPattern("/api/*")
                        .allowedMethods(CloudFrontAllowedMethods.ALL)
//                        .forwardedValues(
//                                CfnDistribution.ForwardedValuesProperty.builder()
//                                        .queryString(true)
//                                        .headers(Arrays.asList("Authorization"))
//                                        .build())
                        .build());

        // source
        List<SourceConfiguration> sourceConfigurationsList = new ArrayList<>(1);
        sourceConfigurationsList.add(
                SourceConfiguration.builder()
                        .s3OriginSource(
                                S3OriginConfig.builder()
                                        .s3BucketSource(siteBucket)
                                        .originAccessIdentity(originAccessIdentity)
                                        .build())
                        .behaviors(s3BehavioursList)
                        .build());
//        sourceConfigurationsList.add(
//                SourceConfiguration.builder()
//                        .customOriginSource(
//                                CustomOriginConfig.builder()
//                                        .domainName(String.format("%s.execute-api.eu-west-1.amazonaws.com",
//                                                api.getRestApiId()))
//                                        .originPath(String.format("/%s", api.getDeploymentStage().getStageName()))
//                                        .build())
//                        .behaviors(apiBehavioursList)
//                        .build());

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
        sources.add(Source.asset("../web-app/dist"));

        BucketDeployment.Builder.create(this, "DeployWithInvalidation")
                .sources(sources)
                .destinationBucket(siteBucket)
                .distribution(distribution)
                .build();

        CfnOutput.Builder.create(this, "CloudFrontDistributionId")
                .description("CloudFront Distribution Id")
                .value(distribution.getDistributionId())
                .build();

        CfnOutput.Builder.create(this, "CloudfrontDistributionURL")
                .description("CloudFront Distribution URL")
                .value(String.format("https://%s", distribution.getDistributionDomainName()))
                .build();
    }
}
