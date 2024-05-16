package com.group76.catalog.usecases.impl

import com.group76.catalog.entities.converters.CVCreateRequestToProductEntity
import com.group76.catalog.entities.converters.CVProductEntityToGetProductResponse
import com.group76.catalog.entities.request.CreateProductRequest
import com.group76.catalog.entities.response.BaseResponse
import com.group76.catalog.entities.response.GetProductResponse
import com.group76.catalog.gateways.ICreateProductGateway
import com.group76.catalog.gateways.IReadProductGateway
import com.group76.catalog.usecases.ICreateProductUseCase
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class CreateProductUseCaseImpl(
    private val createGateway: ICreateProductGateway,
    private val readGateway: IReadProductGateway,
    private val cvCreateRequestToProductEntity: CVCreateRequestToProductEntity,
    private val cvProductEntityToGetProductResponse: CVProductEntityToGetProductResponse
) : ICreateProductUseCase {
    private val log = LoggerFactory.getLogger(CreateProductUseCaseImpl::class.java)

    override fun execute(request: CreateProductRequest): BaseResponse<GetProductResponse> {
        try{
            val nameExists = readGateway.nameExists(request.name)

            if(nameExists) return BaseResponse(
                data = null,
                statusCodes = HttpStatus.BAD_REQUEST,
                error = "Name already exists"
            )

            val response = createGateway.insert(
                cvCreateRequestToProductEntity.convert(request)
            )

            return BaseResponse(
                data = cvProductEntityToGetProductResponse.convert(response),
                statusCodes = HttpStatus.CREATED,
                error = null
            )
        } catch (ex: Exception){
            log.error("Error while inserting product: ${ex.message}", ex)

            return BaseResponse(
                data = null,
                statusCodes = HttpStatus.INTERNAL_SERVER_ERROR,
                error = ex.message
            )
        }
    }
}