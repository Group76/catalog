package com.group76.catalog.usecases

import com.group76.catalog.entities.response.GetProductResponse

interface IGetByIdProductUseCase {
    fun execute(
        id: String
    ): GetProductResponse
}