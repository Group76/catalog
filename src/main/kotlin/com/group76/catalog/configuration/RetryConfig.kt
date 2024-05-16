package com.group76.catalog.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.retry.annotation.EnableRetry
import org.springframework.retry.support.RetryTemplate

@Configuration
@EnableRetry
class RetryConfig {

    @Bean
    fun retryTemplate(): RetryTemplate {
        return RetryTemplate.builder()
            .maxAttempts(3)
            .fixedBackoff(1000)
            .build()
    }
}