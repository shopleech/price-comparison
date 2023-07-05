#!/usr/bin/python3
# @Date:    11.06.2023
# @Author:  Ahto Jalak
from aws_cdk import (
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

    def __init__(self, scope: Construct, id: str, cert: acm.Certificate, user: dict, vpcId: str, **kwargs) -> None:
        super().__init__(scope, id, **kwargs)

        bucket = s3.Bucket.from_bucket_name(
            self, f"price-comparison-web-bucket",
            bucket_name=f"price-comparison-web-assets",
        )

        oin = cloudfront.OriginAccessIdentity(self, 'price-comparison-cf-origin-access-identity')
        bucket.grant_read_write(oin)
        bucket.grant_put_acl(oin)
        # self.bucket.grant_public_access("*", "s3:GetObject")
        # self.bucket.grant_write(iam.User.from_user_arn(self, user['name'], user['arn']))

        distribution = cloudfront.Distribution(
            self, f"price-comparison-website-assets3",
            default_behavior=cloudfront.BehaviorOptions(
                origin=cf_origins.S3Origin(bucket=bucket, origin_access_identity=oin),
                viewer_protocol_policy=cloudfront.ViewerProtocolPolicy.REDIRECT_TO_HTTPS,
            ),
            domain_names=["shopleech.com", "www.shopleech.com"],
            price_class=cloudfront.PriceClass.PRICE_CLASS_100,
            certificate=cert,
        )

        s3_policy_statement = iam.PolicyStatement(
            effect=iam.Effect.ALLOW,
            actions=["s3:GetObject"],
            principals=[
                iam.CanonicalUserPrincipal(oin.cloud_front_origin_access_identity_s3_canonical_user_id),
                iam.ServicePrincipal('cloudfront.amazonaws.com'),
            ],
            resources=[bucket.bucket_arn + ":*"],
        )
        s3_policy_statement.add_condition("StringEquals", {
            "AWS:SourceArn": f"arn:aws:cloudfront::217378209505:distribution/${distribution.distribution_id}",
        })
        bucket.add_to_resource_policy(s3_policy_statement)

        # self.cf_arecord = route53.ARecord(
        #     self, f"website_{id}_record",
        #     zone=route53.HostedZone.from_lookup(self, 'HostedZone', domain_name="shopleech.com", vpc_id=vpcId),
        #     target=route53.RecordTarget.from_alias(r53_targers.CloudFrontTarget(self.distribution)),
        #     record_name="shopleech.com",
        # )
