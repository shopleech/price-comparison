#!/usr/bin/python3
# @Date:    11.06.2023
# @Author:  Ahto Jalak
from aws_cdk import (
    Duration,
    RemovalPolicy,
    aws_s3 as s3,
    aws_iam as iam,
    aws_certificatemanager as acm,
    aws_route53 as route53,
    aws_route53_targets as r53_targers,
    aws_cloudfront as cloudfront,
    aws_cloudfront_origins as cf_origins, Stack,
)
from constructs import Construct


class WebsiteStack(Stack):

    def __init__(self, scope: Construct, id: str, cert: acm.Certificate,
                 domain_name: str, **kwargs) -> None:
        super().__init__(scope, id, **kwargs)

        domain_names = [
            f"www.{domain_name}",
            f"api.{domain_name}",
            domain_name
        ]

        bucket = s3.Bucket(
            self, f"price-comparison-web-bucket",
            public_read_access=False,
            block_public_access=s3.BlockPublicAccess.BLOCK_ALL,
            removal_policy=RemovalPolicy.RETAIN,
            access_control=s3.BucketAccessControl.PRIVATE,
            object_ownership=s3.ObjectOwnership.BUCKET_OWNER_ENFORCED,
            encryption=s3.BucketEncryption.S3_MANAGED,
        )

        oin = cloudfront.OriginAccessIdentity(self, 'price-comparison-cf-origin-access-identity')

        result = bucket.add_to_resource_policy(
            iam.PolicyStatement(
                actions=["s3:GetObject"],
                resources=[bucket.arn_for_objects("*")],
                principals=[iam.CanonicalUserPrincipal(
                    oin.cloud_front_origin_access_identity_s3_canonical_user_id
                )]
            ))

        zone = route53.HostedZone.from_lookup(
            self, 'price-comparison-hosted-zone',
            domain_name=domain_name,
        )

        rewrite = cloudfront.Function(
            self, 'price-comparison-rewrite-function',
            code=cloudfront.FunctionCode.from_file(file_path='functions/url-rewrite.js'),
        )

        my_response_headers_policy = cloudfront.ResponseHeadersPolicy(
            self, "price-comparison-response-headers-policy",
            comment="Security headers response header policy",
            security_headers_behavior=cloudfront.ResponseSecurityHeadersBehavior(
                content_security_policy=cloudfront.ResponseHeadersContentSecurityPolicy(
                    content_security_policy="default-src * gap:; script-src * 'unsafe-inline' 'unsafe-eval'; connect-src *; img-src * data: blob: android-webview-video-poster:; style-src * 'unsafe-inline';",
                    override=True),
                content_type_options=cloudfront.ResponseHeadersContentTypeOptions(
                    override=True),
                frame_options=cloudfront.ResponseHeadersFrameOptions(
                    frame_option=cloudfront.HeadersFrameOption.DENY,
                    override=True),
                referrer_policy=cloudfront.ResponseHeadersReferrerPolicy(
                    referrer_policy=cloudfront.HeadersReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN,
                    override=True),
                strict_transport_security=cloudfront.ResponseHeadersStrictTransportSecurity(
                    access_control_max_age=Duration.days(2 * 365),
                    include_subdomains=True,
                    override=True,
                    preload=True),
                xss_protection=cloudfront.ResponseHeadersXSSProtection(
                    protection=True,
                    mode_block=True,
                    override=True)
            ),
            remove_headers=["Server"],
            server_timing_sampling_rate=50
        )

        distribution = cloudfront.Distribution(
            self, f"price-comparison-website-assets3",
            default_root_object='index.html',
            default_behavior=cloudfront.BehaviorOptions(
                origin=cf_origins.S3Origin(bucket=bucket, origin_access_identity=oin),
                function_associations=[cloudfront.FunctionAssociation(
                    event_type=cloudfront.FunctionEventType.VIEWER_REQUEST,
                    function=rewrite)],
                viewer_protocol_policy=cloudfront.ViewerProtocolPolicy.REDIRECT_TO_HTTPS,
                response_headers_policy=my_response_headers_policy,
            ),
            additional_behaviors={
                "/v1/*": cloudfront.BehaviorOptions(
                    origin=cf_origins.HttpOrigin(
                        domain_name="sl-ecs-prod.sl",
                        protocol_policy=cloudfront.OriginProtocolPolicy.HTTP_ONLY,
                        http_port=8080,
                    ),
                )
            },
            domain_names=domain_names,
            price_class=cloudfront.PriceClass.PRICE_CLASS_100,
            certificate=cert,
        )

        for domain_name in domain_names:
            record = route53.ARecord(
                self, f"price-comparison-record-{domain_name}",
                zone=zone,
                target=route53.RecordTarget.from_alias(r53_targers.CloudFrontTarget(distribution)),
                record_name=domain_name,
            )
