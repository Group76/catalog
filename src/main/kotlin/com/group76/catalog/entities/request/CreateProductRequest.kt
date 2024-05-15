package com.group76.catalog.entities.request

import com.group76.catalog.entities.enum.ProductType
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class CreateProductRequest(
    @NotNull(message = "Name cannot be null")
    val name: String,

    @Size(max = 200, message
    = "Max length of Description is 200 characters")
    val description: String,

    @Min(value = 0.1.toLong(), message = "Price should not be less than 0.1")
    val price: Double,
    val image: String?,

    @NotNull(message = "Type cannot be null")
    val type: ProductType
)
