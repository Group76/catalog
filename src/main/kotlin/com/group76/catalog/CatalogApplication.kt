package com.group76.catalog

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories


@EnableMongoRepositories("com.group76.catalog")
@ConfigurationPropertiesScan("com.group76.catalog")
@ComponentScan("com.group76.catalog")
@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class CatalogApplication

fun main(args: Array<String>) {
	runApplication<CatalogApplication>(*args)
}
