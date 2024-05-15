package com.group76.catalog.usecases

import com.group76.catalog.entities.response.BaseResponse

interface IDeleteProductUseCase {
    fun execute(
        id: String
    ): BaseResponse<Boolean>
}