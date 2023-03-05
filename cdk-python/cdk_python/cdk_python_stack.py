from aws_cdk import (
    # Duration,
    Stack,
    aws_ec2 as ec2,
    aws_iam as iam,
)
from constructs import Construct

from cdk_python.ecs_stack import ContainerServiceStack


class CdkPythonStack(Stack):

    def __init__(self, scope: Construct, construct_id: str, env_vars: dict, **kwargs) -> None:
        super().__init__(scope, construct_id, **kwargs)

        self.vpc_dev = ec2.Vpc.from_lookup(self, "myVpc", vpc_id=env_vars['vpc_name'])

        self.public_subnets = ec2.SubnetSelection(

            subnets=[
                ec2.Subnet.from_subnet_attributes(
                    self, "Publicnet_AZ1", availability_zone="eu-west-1a",
                    subnet_id=env_vars['subnet_name1'],
                    route_table_id=env_vars['route_name1'],
                ),
                ec2.Subnet.from_subnet_attributes(
                    self, "Publicnet_AZ2", availability_zone="eu-west-1b",
                    subnet_id=env_vars['subnet_name2'],
                    route_table_id=env_vars['route_name2'],
                ),
            ],
        )

        self.ecs_cluster = ContainerServiceStack(
            self, "sl-ecs-dev",
            vpc=self.vpc_dev,
            subnets=self.public_subnets,
            sg=ec2.SecurityGroup.from_security_group_id(
                self, f"default-ecs-cluster-sg", env_vars['cluster_sg_name'], mutable=False),
            main_tag="latest-dev",
            key_pair="slkey",
            env={
                'name': 'dev',
                'service_desired_count': 1,
                'region': env_vars['region'],
                'account': env_vars['account'],
            },
        )
