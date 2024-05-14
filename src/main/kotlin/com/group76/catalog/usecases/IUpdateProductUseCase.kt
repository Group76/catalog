package com.group76.catalog.usecases

import com.group76.catalog.entities.request.UpdateProductRequest
import com.group76.catalog.entities.response.GetProductResponse

interface IUpdateProductUseCase {
    fun execute(
        request: UpdateProductRequest
    ): GetProductResponse
}