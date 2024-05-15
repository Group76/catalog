package com.group76.catalog.usecases.impl

import com.group76.catalog.entities.converters.CVProductEntityToGetProductResponse
import com.group76.catalog.entities.converters.CVUpdateRequestToProductEntity
import com.group76.catalog.entities.request.UpdateProductRequest
import com.group76.catalog.entities.response.BaseResponse
import com.group76.catalog.entities.response.GetProductResponse
import com.group76.catalog.gateways.IUpdateProductGateway
import com.group76.catalog.usecases.IUpdateProductUseCase
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class UpdateProductUseCaseImpl(
    private val updateGateway: IUpdateProductGateway,
    private val cvUpdateRequestToProductEntity: CVUpdateRequestToProductEntity,
    private val cvProductEntityToGetProductResponse: CVProductEntityToGetProductResponse
) : IUpdateProductUseCase {
    private val log = LoggerFactory.getLogger(UpdateProductUseCaseImpl::class.java)

    override fun execute(request: UpdateProductRequest): BaseResponse<GetProductResponse> {
        try{
            val entity = cvUpdateRequestToProductEntity.convert(request)

            val response = updateGateway.update(
                entity
            )

            if(response) {
                return BaseResponse(
                    data = cvProductEntityToGetProductResponse.convert(entity),
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
            log.error("Error while updating product: ${ex.message}", ex)

            return BaseResponse(
                data = null,
                statusCodes = HttpStatus.INTERNAL_SERVER_ERROR,
                error = ex.message
            )
        }
    }
}