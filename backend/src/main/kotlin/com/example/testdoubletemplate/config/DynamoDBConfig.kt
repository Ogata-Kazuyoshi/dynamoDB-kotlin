package com.example.testdoubletemplate.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import java.net.URI

@Configuration
class DynamoDBConfig () {
    @Bean
    @Profile("local")
    fun dynamoDBClientInLocal(
        @Value("\${dynamodb.endpoint}")
        endpoint: String,
        @Value("\${dynamodb.region}")
        region: String,
    ): DynamoDbClient {
        return DynamoDbClient.builder()
            .endpointOverride(URI.create(endpoint))
            .region(Region.of(region))
            .credentialsProvider(
                StaticCredentialsProvider.create(
                AwsBasicCredentials.create("dummyId", "dummyKey")
            ))
            .build()
    }

    @Bean
    @Profile("!local")
    fun dynamoDBClientInOther(): DynamoDbClient {
        return DynamoDbClient.builder()
            .build()
    }

    @Bean
    fun dynamoDBEnhancedClient(
        dynamoDbClient: DynamoDbClient
    ): DynamoDbEnhancedClient {
        return DynamoDbEnhancedClient.builder()
            .dynamoDbClient(dynamoDbClient)
            .build()
    }
}