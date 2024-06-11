package com.group76.catalog.service.impl

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.group76.catalog.entities.request.ProductMessageSns
import com.group76.catalog.service.ISnsService
import org.springframework.stereotype.Component
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider
import software.amazon.awssdk.http.SdkHttpResponse
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sns.SnsClient
import software.amazon.awssdk.services.sns.model.PublishRequest
import software.amazon.awssdk.services.sns.model.Topic

@Component
class SnsServiceImpl : ISnsService {

    private var topicList = mutableListOf<Topic>()
    private val objectMapper = jacksonObjectMapper()

    private val snsClient: SnsClient = SnsClient.builder()
        .region(Region.US_EAST_2)
        .credentialsProvider(DefaultCredentialsProvider.create())
        .build()

    override fun publishMessage(topicArn: String, message: ProductMessageSns, subject: String): SdkHttpResponse {
        val messageJson = objectMapper.writeValueAsString(message)

        val publishRequest = PublishRequest.builder()
            .topicArn(topicArn)
            .message(messageJson)
            .messageGroupId("product")
            .subject(subject)
            .build()

        val publishResponse = snsClient.publish(publishRequest)
        return publishResponse.sdkHttpResponse()
    }

    override fun getTopicArnByName(topicName: String): String? {
        if(topicList.isEmpty()){
            var nextToken: String? = null

            do {
                val listTopicsResponse = snsClient.listTopics { it.nextToken(nextToken) }
                topicList.addAll(listTopicsResponse.topics())
                nextToken = listTopicsResponse.nextToken()
            } while (nextToken != null)
        }

        return topicList.find { it.topicArn().endsWith(":$topicName") }?.topicArn()
    }
}