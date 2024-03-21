#!/usr/bin/python3
# @Time     : 05.03.2023
# @Author   : Ahto Jalak
import aws_cdk as cdk
from aws_cdk import (
    Duration,
    RemovalPolicy,
    Stack,
    aws_autoscaling as autoscaling,
    aws_ec2 as ec2,
    aws_elasticloadbalancingv2 as elbv2,
    aws_ecs as ecs,
    aws_ecr as ecr,
    aws_iam as iam,
    aws_certificatemanager as acm,
    aws_logs as logs,
    aws_servicediscovery as sd,
)
from constructs import Construct


class ContainerServiceStack(Stack):

    def __init__(self, scope: Construct, id: str, vpc: object, subnets: ec2.SubnetSelection,
                 key_pair: str, main_tag: str, env: dict, **kwargs) -> None:
        super().__init__(scope, id, **kwargs)

        cluster = ecs.Cluster(
            self, id,
            cluster_name=id,
            vpc=vpc,
            enable_fargate_capacity_providers=True,
            container_insights=True,
        )

        # Adding service discovery namespace to cluster
        cluster.add_default_cloud_map_namespace(
            name=f"{id}.local",
        )

        task_role = iam.Role(
            self, f"{id}-task-execution-role",
            assumed_by=iam.ServicePrincipal("ecs-tasks.amazonaws.com"),
        )

        task_role.add_managed_policy(
            iam.ManagedPolicy.from_aws_managed_policy_name(
                "service-role/AmazonECSTaskExecutionRolePolicy"
            )
        )

        service_log_group = logs.LogGroup(
            self, f"{id}-log-group1",
            log_group_name=f"{id}-service-log1",
            removal_policy=RemovalPolicy.RETAIN,
        )

        service_log_driver = ecs.AwsLogDriver(log_group=service_log_group, stream_prefix=f"{id}-log-stream")

        service_sg = ec2.SecurityGroup(
            self, f"{id}-sg",
            allow_all_outbound=True,
            security_group_name=f"{id}-sg",
            vpc=vpc,
            disable_inline_rules=True,
        )
        service_sg.add_ingress_rule(ec2.Peer.any_ipv4(), ec2.Port.tcp(22), "allow ssh access from the world")
        service_sg.add_ingress_rule(ec2.Peer.any_ipv4(), ec2.Port.tcp(8080), "allow ssh access from the world")

        # BACKEND
        task_definition = ecs.FargateTaskDefinition(
            self, f"{id}-public-api",
            task_role=task_role,
            family=f"{id}_public-api",
        )

        container = task_definition.add_container(
            f"{id}_task_container2",
            image=ecs.ContainerImage.from_ecr_repository(
                repository=ecr.Repository.from_repository_name(
                    self, f"{id}-task-repo",
                    repository_name="sl-public-api",
                ),
                tag=main_tag,
            ),
            logging=service_log_driver,
            memory_limit_mib=512,
            cpu=256,
            environment={},
            linux_parameters=ecs.LinuxParameters(
                self, f"{id}-task-linux-parameters",
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

        ecs_service = ecs.FargateService(
            self, f"{id}-public-api-service",
            service_name=f"{id}-public-api-service",
            cluster=cluster,
            task_definition=task_definition,
            desired_count=1,
            vpc_subnets=subnets,
            assign_public_ip=True,
            security_groups=[service_sg],
            cloud_map_options=ecs.CloudMapOptions(name="backend"),
            # deployment_alarms=ecs.DeploymentAlarmConfig(
            #    alarm_names=[elb_alarm.alarm_name],
            #    behavior=ecs.AlarmBehavior.ROLLBACK_ON_ALARM
            # ),
        )

        ecs_service.connections.allow_from_any_ipv4(
            ec2.Port.tcp(8080), "flask inbound"
        )

        # Setup AutoScaling policy
        scaling = ecs_service.auto_scale_task_count(
            max_capacity=1
        )
        scaling.scale_on_cpu_utilization(
            f"{id}CpuScaling",
            target_utilization_percent=50,
            scale_in_cooldown=Duration.seconds(60),
            scale_out_cooldown=Duration.seconds(60),
        )
