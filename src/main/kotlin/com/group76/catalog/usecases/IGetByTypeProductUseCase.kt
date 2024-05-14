package com.group76.catalog.usecases

import com.group76.catalog.entities.enum.ProductType
import com.group76.catalog.entities.response.GetProductResponse
import org.springframework.data.domain.Page

interface IGetByTypeProductUseCase {
    fun execute(
        type: ProductType
    ): Page<GetProductResponse>
}