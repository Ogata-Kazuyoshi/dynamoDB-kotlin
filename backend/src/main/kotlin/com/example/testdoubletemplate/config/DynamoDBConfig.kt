package com.example.testdoubletemplate.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import java.net.URI

@Configuration
class DynamoDBConfig (
    @Value("\${dynamodb.endpoint}")
    private val endopoint: String,
    @Value("\${dynamodb.region}")
    private val region: String,
) {
    @Bean
    @Profile("local")
    fun dynamoDBClientInLocal(): DynamoDbClient {
        return DynamoDbClient.builder()
            .endpointOverride(URI.create(endopoint))
            .region(Region.of(region))
            .build()
    }

    @Bean
    @Profile("!local")
    fun dynamoDBClientInOther(): DynamoDbClient {
        return DynamoDbClient.builder()
            .endpointOverride(URI.create(endopoint))
            .region(Region.of(region))
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