package com.luxious.lmsd.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.luxious.lmsd")
public class AWSConfig {
    @Value("${awsAccesskey}")
    private String awsAccessKey;

    @Value("${awsSecretkey}")
    private String awsSecretKey;

    @Bean
    public AWSCredentials awsCredentials() {
        System.out.print("credentials");
        return new BasicAWSCredentials(awsAccessKey, awsSecretKey);
    }

    @Bean
    @Autowired
    public AmazonDynamoDB amazonDynamoDB(AWSCredentials awsCredentials) {
        System.out.print("amamzonDYDB");

        return AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_WEST_2)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
    }

    @Bean
    @Autowired
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB) {
        DynamoDBMapperConfig mapperConfig =
                DynamoDBMapperConfig.builder()
                        .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT).build();
        return new DynamoDBMapper(amazonDynamoDB, mapperConfig);
    }
}
