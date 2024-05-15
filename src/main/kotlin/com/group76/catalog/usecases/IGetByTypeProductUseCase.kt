package com.group76.catalog.usecases

import com.group76.catalog.entities.enum.ProductType
import com.group76.catalog.entities.response.BaseResponse
import com.group76.catalog.entities.response.GetProductResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface IGetByTypeProductUseCase {
    fun execute(
        type: ProductType,
        pageable: Pageable
    ): BaseResponse<Page<GetProductResponse>>
}