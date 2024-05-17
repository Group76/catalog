package com.group76.catalog.usecases.impl

import com.group76.catalog.configuration.SystemProperties
import com.group76.catalog.entities.converters.CVCreateRequestToProductEntity
import com.group76.catalog.entities.converters.CVProductEntityToGetProductResponse
import com.group76.catalog.entities.converters.CVProductEntityToSnsMessage
import com.group76.catalog.entities.enum.ProductOperation
import com.group76.catalog.entities.request.CreateProductRequest
import com.group76.catalog.entities.response.BaseResponse
import com.group76.catalog.entities.response.GetProductResponse
import com.group76.catalog.gateways.ICreateProductGateway
import com.group76.catalog.gateways.IReadProductGateway
import com.group76.catalog.service.SnsService
import com.group76.catalog.usecases.ICreateProductUseCase
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class CreateProductUseCaseImpl(
    private val createGateway: ICreateProductGateway,
    private val readGateway: IReadProductGateway,
    private val cvCreateRequestToProductEntity: CVCreateRequestToProductEntity,
    private val cvProductEntityToGetProductResponse: CVProductEntityToGetProductResponse,
    private val snsService: SnsService,
    private val cvProductEntityToSnsMessage: CVProductEntityToSnsMessage,
    private val systemProperties : SystemProperties
) : ICreateProductUseCase {
    private val log = LoggerFactory.getLogger(CreateProductUseCaseImpl::class.java)

    override fun execute(request: CreateProductRequest): BaseResponse<GetProductResponse> {
        try{
            val nameExists = readGateway.nameExists(name = request.name)

            if(nameExists) return BaseResponse(
                data = null,
                statusCodes = HttpStatus.BAD_REQUEST,
                error = "Name already exists"
            )

            val response = createGateway.insert(
                cvCreateRequestToProductEntity.convert(request)
            )

            snsService.publishMessage(
                snsService.getTopicArnByName(systemProperties.sns.product)!!,
                cvProductEntityToSnsMessage.convert(response, ProductOperation.CREATED),
                "Product Created"
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