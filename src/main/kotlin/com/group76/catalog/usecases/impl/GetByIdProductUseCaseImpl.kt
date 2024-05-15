package com.group76.catalog.usecases.impl

import com.group76.catalog.entities.converters.CVProductEntityToGetProductResponse
import com.group76.catalog.entities.response.BaseResponse
import com.group76.catalog.entities.response.GetProductResponse
import com.group76.catalog.gateways.IReadProductGateway
import com.group76.catalog.usecases.IGetByIdProductUseCase
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class GetByIdProductUseCaseImpl(
    private val readProductGateway: IReadProductGateway,
    private val cvProductEntityToGetProductResponse: CVProductEntityToGetProductResponse
) : IGetByIdProductUseCase {
    private val log = LoggerFactory.getLogger(GetByIdProductUseCaseImpl::class.java)

    override fun execute(id: String): BaseResponse<GetProductResponse> {
        try {
            val entity = readProductGateway
                .findById(id) ?: return BaseResponse(
                data = null,
                statusCodes = HttpStatus.NOT_FOUND,
                error = null
            )

            return BaseResponse(
                data = cvProductEntityToGetProductResponse.convert(entity),
                statusCodes = HttpStatus.OK,
                error = null
            )
        } catch (ex: Exception){
            log.error("Error while get product by id: ${ex.message}", ex)

            return BaseResponse(
                data = null,
                statusCodes = HttpStatus.INTERNAL_SERVER_ERROR,
                error = ex.message
            )
        }
    }
}