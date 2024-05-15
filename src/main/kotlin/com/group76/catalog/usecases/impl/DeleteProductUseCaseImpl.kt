package com.group76.catalog.usecases.impl

import com.group76.catalog.entities.response.BaseResponse
import com.group76.catalog.gateways.IDeleteProductGateway
import com.group76.catalog.usecases.IDeleteProductUseCase
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class DeleteProductUseCaseImpl(
    private val deleteGateway: IDeleteProductGateway,
) : IDeleteProductUseCase {
    private val log = LoggerFactory.getLogger(DeleteProductUseCaseImpl::class.java)
    override fun execute(id: String): BaseResponse<Boolean> {
        try {
            val response = deleteGateway.delete(id)

            if(response) {
                return BaseResponse(
                    data = null,
                    statusCodes = HttpStatus.OK,
                    error = null
                )
            } else {
                log.error("Update was not acknowledged")

                return BaseResponse(
                    data = null,
                    statusCodes = HttpStatus.INTERNAL_SERVER_ERROR,
                    error = "Update was not acknowledged"
                )
            }
        } catch (ex: Exception){
            log.error("Error while deleting product: ${ex.message}", ex)

            return BaseResponse(
                data = null,
                statusCodes = HttpStatus.INTERNAL_SERVER_ERROR,
                error = ex.message
            )
        }
    }
}