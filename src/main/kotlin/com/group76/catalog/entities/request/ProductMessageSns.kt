package com.group76.catalog.entities.request

import com.group76.catalog.entities.enum.ProductOperation
import com.group76.catalog.entities.enum.ProductType

data class ProductMessageSns(
    val id: String,
    val name: String?,
    val description: String?,
    val price: Double?,
    val type: ProductType?,
    val operation: ProductOperation
)