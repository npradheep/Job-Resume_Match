package com.resumematch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

@Configuration
public class DynamoDBConfig {
	@Value("${aws.access.key.id}")
    private String awsKeyIddb;

    @Value("${aws.access.key.secret}")
    private String awsKeySecretdb;

    @Value("${aws.region}")
    private String awsRegiondb;
    
    @Value("${aws.dynamodb.table}")
    private String dbTableName;
    
    @Bean(name = "dbTableName")
    public String getDbTableName() {
		return dbTableName;
	}
    
    @Bean(name = "awsKeyIddb")
    public String getAWSKeyIddb() {
        return awsKeyIddb;
    }

    @Bean(name = "awsKeySecretdb")
    public String getAWSKeySecretdb() {
        return awsKeySecretdb;
    }

    @Bean(name = "awsRegiondb")
    public Region getAWSPollyRegiondb() {
        return Region.getRegion(Regions.fromName(awsRegiondb));
    }

    @Bean(name = "awsCredentialsProviderdb")
    public AWSCredentialsProvider getAWSCredentialsdb() {
        BasicAWSCredentials awsCredentialsdb = new BasicAWSCredentials(this.awsKeyIddb, this.awsKeySecretdb);
        return new AWSStaticCredentialsProvider(awsCredentialsdb);
    }
    
   
}