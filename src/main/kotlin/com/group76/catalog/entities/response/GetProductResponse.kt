package com.group76.catalog.entities.response

import com.group76.catalog.entities.enum.ProductType

data class GetProductResponse(
    val id: String,
    val name: String,
    val price: Double,
    val image: String?,
    val type: ProductType
)
