package shopleech;

import software.amazon.awscdk.Duration;
import software.amazon.awscdk.RemovalPolicy;
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
import software.amazon.awscdk.services.route53.targets.CloudFrontTarget;
import software.amazon.awscdk.services.s3.Bucket;
import software.constructs.Construct;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;

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

        // defines certificate
        final IHostedZone zone =
                HostedZone.fromLookup(
                        this,
                        "Zone",
                        HostedZoneProviderProps.builder()
                                .domainName("shopleech.com")
                                .build());
        List<String> siteDomainList = new ArrayList<>(1);
        siteDomainList.add("shopleech.com");

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
                        // The default removal policy is RETAIN, which means that cdk destroy will not attempt
                        // to delete
                        // the new bucket, and it will remain in your account until manually deleted. By
                        // setting the policy to
                        // DESTROY, cdk destroy will attempt to delete the bucket, but will error if the
                        // bucket is not empty.
                        .removalPolicy(RemovalPolicy.RETAIN)
                        .build();

        // Defines a cloudfront
        List<Behavior> behavioursList = new ArrayList<>(1);
        behavioursList.add(Behavior.builder().isDefaultBehavior(true).build());

        List<SourceConfiguration> sourceConfigurationsList = new ArrayList<>(1);
        sourceConfigurationsList.add(
                SourceConfiguration.builder()
                        .s3OriginSource(S3OriginConfig.builder().s3BucketSource(siteBucket).build())
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
                        .code(Code.fromAsset("./assets/product-api-0.1.0.jar"))
                        .handler("hello.handler")
                        .runtime(Runtime.JAVA_11)
                        .environment(lambdaEnvMap)
                        .timeout(Duration.seconds(30))
                        .memorySize(512)
                        .build()
        );

        dynamodbTable.grantReadWriteData(productApiFunction);

        // defines a new api gateway
        RestApi api = new RestApi(this, "productApi",
                RestApiProps.builder().restApiName("Product Service").build());

        // set endpoint
        IResource items = api.getRoot().addResource("products");
        Integration getAllIntegration = new LambdaIntegration(productApiFunction);
        items.addMethod("GET", getAllIntegration);

        // set endpoint
        IResource singleItem = items.addResource("{id}");
        Integration getOneIntegration = new LambdaIntegration(productApiFunction);
        singleItem.addMethod("GET", getOneIntegration);

    }

    // Source: https://github.com/aws-samples/aws-cdk-examples/blob/master/java/api-cors-lambda-crud-dynamodb/cdk/src/main/java/software/amazon/awscdk/examples/CorsLambdaCrudDynamodbStack.java
    private void addCorsOptions(IResource item) {
        List<MethodResponse> methoedResponses = new ArrayList<>();

        Map<String, Boolean> responseParameters = new HashMap<>();
        responseParameters.put("method.response.header.Access-Control-Allow-Headers", Boolean.TRUE);
        responseParameters.put("method.response.header.Access-Control-Allow-Methods", Boolean.TRUE);
        responseParameters.put("method.response.header.Access-Control-Allow-Credentials", Boolean.TRUE);
        responseParameters.put("method.response.header.Access-Control-Allow-Origin", Boolean.TRUE);
        methoedResponses.add(MethodResponse.builder()
                .responseParameters(responseParameters)
                .statusCode("200")
                .build());
        MethodOptions methodOptions = MethodOptions.builder()
                .methodResponses(methoedResponses)
                .build();

        Map<String, String> requestTemplate = new HashMap<>();
        requestTemplate.put("application/json", "{\"statusCode\": 200}");
        List<IntegrationResponse> integrationResponses = new ArrayList<>();

        Map<String, String> integrationResponseParameters = new HashMap<>();
        integrationResponseParameters.put("method.response.header.Access-Control-Allow-Headers", "'Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token,X-Amz-User-Agent'");
        integrationResponseParameters.put("method.response.header.Access-Control-Allow-Origin", "'*'");
        integrationResponseParameters.put("method.response.header.Access-Control-Allow-Credentials", "'false'");
        integrationResponseParameters.put("method.response.header.Access-Control-Allow-Methods", "'OPTIONS,GET,PUT,POST,DELETE'");
        integrationResponses.add(IntegrationResponse.builder()
                .responseParameters(integrationResponseParameters)
                .statusCode("200")
                .build());
        Integration methodIntegration = MockIntegration.Builder.create()
                .integrationResponses(integrationResponses)
                .passthroughBehavior(PassthroughBehavior.NEVER)
                .requestTemplates(requestTemplate)
                .build();

        item.addMethod("OPTIONS", methodIntegration, methodOptions);
    }

}
