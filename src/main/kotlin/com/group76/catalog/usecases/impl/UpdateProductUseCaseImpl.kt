package com.group76.catalog.usecases.impl

import com.group76.catalog.configuration.SystemProperties
import com.group76.catalog.entities.converters.CVProductEntityToGetProductResponse
import com.group76.catalog.entities.converters.CVProductEntityToSnsMessage
import com.group76.catalog.entities.converters.CVUpdateRequestToProductEntity
import com.group76.catalog.entities.enum.ProductOperation
import com.group76.catalog.entities.request.UpdateProductRequest
import com.group76.catalog.entities.response.BaseResponse
import com.group76.catalog.entities.response.GetProductResponse
import com.group76.catalog.gateways.IReadProductGateway
import com.group76.catalog.gateways.IUpdateProductGateway
import com.group76.catalog.service.SnsService
import com.group76.catalog.usecases.IUpdateProductUseCase
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class UpdateProductUseCaseImpl(
    private val readGateway: IReadProductGateway,
    private val updateGateway: IUpdateProductGateway,
    private val cvUpdateRequestToProductEntity: CVUpdateRequestToProductEntity,
    private val cvProductEntityToGetProductResponse: CVProductEntityToGetProductResponse,
    private val snsService: SnsService,
    private val cvProductEntityToSnsMessage: CVProductEntityToSnsMessage,
    private val systemProperties : SystemProperties
) : IUpdateProductUseCase {
    private val log = LoggerFactory.getLogger(UpdateProductUseCaseImpl::class.java)

    override fun execute(request: UpdateProductRequest): BaseResponse<GetProductResponse> {
        try{
            val error = validate(id = request.id, name = request.name)
            if(error != null) return error

            val entity = cvUpdateRequestToProductEntity.convert(request)

            val response = updateGateway.update(
                entity
            )

            if(response) {
                snsService.publishMessage(
                    snsService.getTopicArnByName(systemProperties.sns.product)!!,
                    cvProductEntityToSnsMessage.convert(entity, ProductOperation.UPDATED),
                    "Product Updated"
                )

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

    fun validate(id: String, name: String) : BaseResponse<GetProductResponse>? {
        val productExists = readGateway.productExists(id)

        if(productExists) return BaseResponse(
            data = null,
            statusCodes = HttpStatus.BAD_REQUEST,
            error = "Product not exists or it was deleted"
        )

        val nameExists = readGateway.nameExists(id, name)

        if(nameExists) return BaseResponse(
            data = null,
            statusCodes = HttpStatus.BAD_REQUEST,
            error = "Name already exists"
        )

        return null
    }
}