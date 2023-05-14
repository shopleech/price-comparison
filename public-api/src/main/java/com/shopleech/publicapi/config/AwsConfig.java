package com.shopleech.publicapi.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/**
 * @author Ahto Jalak
 * @since 14.05.2023
 */
@Configuration
public class AwsConfig {

    @Value("${aws.access-key}")
    private String awsAccessKey;
    @Value("${aws.access-secret-key}")
    private String awsAccessSecretKey;
    @Value("${aws.region}")
    private String awsRegion;

//    @Bean
//    public AWSStaticCredentialsProvider getAwsCredentialsProvider() {
//        BasicAWSCredentials awsCred = new BasicAWSCredentials(awsAccessKey, awsAccessSecretKey);
//        return new AWSStaticCredentialsProvider(awsCred);
//    }

    @Bean
    public AWSCredentialsProvider amazonAWSCredentialsProvider() {
        return DefaultAWSCredentialsProviderChain.getInstance();
    }

    @Bean
    public AmazonS3 getAmazonS3Client() {
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(awsRegion)
                .withCredentials(amazonAWSCredentialsProvider())
                .build();
    }
}
