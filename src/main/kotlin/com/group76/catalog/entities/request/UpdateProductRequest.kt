package com.group76.catalog.entities.request

import com.group76.catalog.entities.enum.ProductType

data class UpdateProductRequest(
    val id: String,
    val name: String,
    val price: Double,
    val image: String,
    val type: ProductType
)
