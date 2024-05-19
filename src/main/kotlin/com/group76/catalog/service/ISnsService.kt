package com.group76.catalog.service

import com.group76.catalog.entities.request.ProductMessageSns
import software.amazon.awssdk.http.SdkHttpResponse

interface ISnsService {
    fun publishMessage(
        topicArn: String,
        message: ProductMessageSns,
        subject: String
    ): SdkHttpResponse

    fun getTopicArnByName(
        topicName: String
    ): String?
}