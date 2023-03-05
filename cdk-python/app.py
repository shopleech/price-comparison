#!/usr/bin/env python3
import os

import aws_cdk as cdk

from cdk_python.cdk_python_stack import CdkPythonStack

env_dev = {
    'account': '123',
    'region': 'eu-west-1',
    'vpc_name': 'vpc-123',
    'subnet_name1': 'subnet-122',
    'route_name1': 'rtb-122',
    'subnet_name2': 'subnet-123',
    'route_name2': 'rt-123',
    'cluster_sg_name': 'sg-123',
}

app = cdk.App()
CdkPythonStack(
    app, "CdkPythonStack",
    env_vars=env_dev,
    env=cdk.Environment(account=env_dev['account'], region=env_dev['region']),
    # TODO: use environment variables
    # env=cdk.Environment(account=os.getenv('CDK_DEFAULT_ACCOUNT'), region=os.getenv('CDK_DEFAULT_REGION')),
    # For more information, see https://docs.aws.amazon.com/cdk/latest/guide/environments.html
)

app.synth()
