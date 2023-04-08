#!/usr/bin/python3
# @Time     : 05.03.2023
# @Author   : Ahto Jalak
import aws_cdk as cdk
from aws_cdk import (
    Duration,
    Stack,
    aws_autoscaling as autoscaling,
    aws_ec2 as ec2,
    aws_elasticloadbalancingv2 as elbv2,
    aws_ecs as ecs,
    aws_ecr as ecr,
    aws_iam as iam,
    aws_certificatemanager as acm,
    aws_servicediscovery as sd,
)
from constructs import Construct


class ContainerServiceStack(Stack):

    def __init__(self, scope: Construct, id: str, vpc: object, subnets: ec2.SubnetSelection,
                 sg: ec2.SecurityGroup, key_pair: str, main_tag: str, env: dict, **kwargs) -> None:
        super().__init__(scope, id, **kwargs)

        cluster = ecs.Cluster(
            self, id,
            cluster_name=id,
            vpc=vpc,
            capacity=ecs.AddCapacityOptions(
                instance_type=ec2.InstanceType("t3a.micro"),
                key_name=key_pair,
                desired_capacity=1,
                associate_public_ip_address=True,
                vpc_subnets=ec2.SubnetSelection(
                    subnet_type=ec2.SubnetType.PUBLIC
                ),
            ),
        )

        namespace = sd.PrivateDnsNamespace(
            self, f"{id}_examplePrivateDnsNamespace",
            name=f"{id}.sldev",
            description=f"{id}.sldev",
            vpc=vpc,
        )

        service = namespace.create_service(
            f"{id}_NamespaceService1",
            name=f"{id}-namespace-service",
            dns_record_type=sd.DnsRecordType.A,
            dns_ttl=Duration.seconds(30),
            # health_check=cdk.aws_servicediscovery.HealthCheckConfig(
            #   type=sd.HealthCheckType.HTTP,
            #   resource_path="/health",
            #   failure_threshold=2,
            # )
        )

        task_role = iam.Role(
            self, f"{id}_ecsTaskExecutionRole", assumed_by=iam.ServicePrincipal("ecs-tasks.amazonaws.com"))

        task_role.add_managed_policy(
            iam.ManagedPolicy.from_aws_managed_policy_name(
                "service-role/AmazonECSTaskExecutionRolePolicy"
            )
        )

        # BACKEND
        task_definition = ecs.Ec2TaskDefinition(
            self, f"{id}_public-api",
            task_role=task_role,
            family=f"{id}_public-api",
            network_mode=ecs.NetworkMode.HOST,
            # volumes=[
            #     ecs.Volume(
            #         name=f"{id}_efs-ec2",
            #         efs_volume_configuration=ecs.EfsVolumeConfiguration(
            #             file_system_id=env['file_system_id'],
            #             transit_encryption="ENABLED"))
            # ],
        )

        container = task_definition.add_container(
            f"{id}_task_container2",
            image=ecs.ContainerImage.from_ecr_repository(
                repository=ecr.Repository.from_repository_name(
                    self, f"{id}_task_repo",
                    repository_name="sl-public-api",
                ),
                tag=main_tag,
            ),
            logging=ecs.LogDrivers.aws_logs(stream_prefix=id),
            memory_limit_mib=640,
            cpu=1024,
            environment={},
            linux_parameters=ecs.LinuxParameters(
                self, f"{id}_linux_parameters",
                init_process_enabled=True,
            ),
        )

        port_mapping = ecs.PortMapping(
            container_port=8080,
            host_port=8080,
            protocol=ecs.Protocol.TCP,
        )
        container.add_port_mappings(port_mapping)
        # container.add_mount_points(
        #     ecs.MountPoint(container_path="/sldisk", source_volume=f"{id}_efs-ec2", read_only=False)
        # )

        # sg.connections.allow_from(port_range=ec2.Port.tcp(80))

        ecs_service = ecs.Ec2Service(
            self, f"{id}_public-api-service",
            service_name=f"{id}_public-api-service",
            cluster=cluster,
            task_definition=task_definition,
            min_healthy_percent=0,  # TODO: should be at least 50 for ZDT
            max_healthy_percent=200,
            # assign_public_ip=True,
            # vpc_subnets=subnets,
            # desired_count=1,
            # security_groups=[sg],
            # cloud_map_options=ecs.CloudMapOptions(
            #   name="sl", cloud_map_namespace=example_private_dns_namespace, dns_record_type=sd.DnsRecordType.A)
        )

        ecs_service.connections.allow_from_any_ipv4(port_range=ec2.Port.tcp(22))
        ecs_service.connections.allow_from_any_ipv4(port_range=ec2.Port.tcp(8080))

        # FRONTEND
        task_definition3 = ecs.Ec2TaskDefinition(
            self, f"{id}_client",
            task_role=task_role,
            family=f"{id}_client",
            network_mode=ecs.NetworkMode.HOST,
            # volumes=[
            #     ecs.Volume(
            #         name=f"{id}_efs-ec2",
            #         efs_volume_configuration=ecs.EfsVolumeConfiguration(
            #             file_system_id=env['file_system_id'],
            #             transit_encryption="ENABLED"))
            # ],
        )

        container3 = task_definition3.add_container(
            f"{id}_task_container3",
            image=ecs.ContainerImage.from_ecr_repository(
                repository=ecr.Repository.from_repository_name(
                    self, f"{id}_task_repo3",
                    repository_name="sl-client",
                ),
                tag=main_tag,
            ),
            logging=ecs.LogDrivers.aws_logs(stream_prefix=f"{id}3"),
            memory_limit_mib=256,
            cpu=512,
            environment={},
            linux_parameters=ecs.LinuxParameters(
                self, f"{id}_linux_parameters3",
                init_process_enabled=True,
            ),
        )

        port_mapping3 = ecs.PortMapping(
            container_port=80,
            host_port=80,
            protocol=ecs.Protocol.TCP,
        )
        container3.add_port_mappings(port_mapping3)
        # container.add_mount_points(
        #     ecs.MountPoint(container_path="/sldisk", source_volume=f"{id}_efs-ec2", read_only=False)
        # )

        # sg.connections.allow_from(port_range=ec2.Port.tcp(80))

        # backend
        ecs_service3 = ecs.Ec2Service(
            self, f"{id}_client-service",
            service_name=f"{id}_client-service",
            cluster=cluster,
            task_definition=task_definition3,
            min_healthy_percent=0,  # TODO: should be at least 50 for ZDT
            max_healthy_percent=200,
            # assign_public_ip=True,
            # vpc_subnets=subnets,
            # desired_count=1,
            # security_groups=[sg],
            # cloud_map_options=ecs.CloudMapOptions(
            #   name="sl", cloud_map_namespace=example_private_dns_namespace, dns_record_type=sd.DnsRecordType.A)
        )

        ecs_service3.connections.allow_from_any_ipv4(port_range=ec2.Port.tcp(22))
        ecs_service3.connections.allow_from_any_ipv4(port_range=ec2.Port.tcp(80))
