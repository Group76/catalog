package com.group76.catalog.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "system")
class SystemProperties {
    var idService: String = ""
    val collection: CollectionConfiguration = CollectionConfiguration()
    val sns: SnsConfiguration = SnsConfiguration()

    class CollectionConfiguration{
        var product: String = ""
    }

    class SnsConfiguration{
        var product: String = ""
    }
}

