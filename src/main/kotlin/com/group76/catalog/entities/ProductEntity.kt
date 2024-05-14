package com.group76.catalog.entities

import com.group76.catalog.entities.enum.ProductType
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("#{@environment.getProperty('system.collection.product')}")
data class ProductEntity (
    @Id
    val id: String?,
    val name: String,
    val price: Double,
    val image: String,
    val deleted: Boolean,
    val type: ProductType
)