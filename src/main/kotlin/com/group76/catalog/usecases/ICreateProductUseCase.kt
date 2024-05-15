package com.group76.catalog.usecases

import com.group76.catalog.entities.request.CreateProductRequest
import com.group76.catalog.entities.response.BaseResponse
import com.group76.catalog.entities.response.GetProductResponse

interface ICreateProductUseCase {
    fun execute(
        request: CreateProductRequest
    ): BaseResponse<GetProductResponse>
}