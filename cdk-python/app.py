#!/usr/bin/env python3
import os

import aws_cdk as cdk

from cdk_python.cdk_python_stack import CdkPythonStack
from jproperties import Properties

configs = Properties()
with open('app-config.properties', 'rb') as config_file:
    configs.load(config_file)

env_dev = {}
for item in configs.items():
    env_dev[item[0]] = item[1].data

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
