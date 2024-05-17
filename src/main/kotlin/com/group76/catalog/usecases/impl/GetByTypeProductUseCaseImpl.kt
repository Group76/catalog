package com.group76.catalog.usecases.impl

import com.group76.catalog.configuration.SystemProperties
import com.group76.catalog.entities.converters.CVProductEntityToGetProductResponse
import com.group76.catalog.entities.enum.ProductType
import com.group76.catalog.entities.response.BaseResponse
import com.group76.catalog.entities.response.GetProductResponse
import com.group76.catalog.gateways.IReadProductGateway
import com.group76.catalog.usecases.IGetByTypeProductUseCase
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class GetByTypeProductUseCaseImpl(
    private val readProductGateway: IReadProductGateway,
    private val cvProductEntityToGetProductResponse: CVProductEntityToGetProductResponse
) : IGetByTypeProductUseCase {
    private val log = LoggerFactory.getLogger(GetByTypeProductUseCaseImpl::class.java)
    override fun execute(
        type: ProductType,
        pageable: Pageable
        ): BaseResponse<Page<GetProductResponse>> {
        try {
            val response = readProductGateway.findByType(type, pageable).map {
                cvProductEntityToGetProductResponse.convert(it)
            }

            return BaseResponse(
                data = response,
                statusCodes = HttpStatus.OK,
                error = null
            )

        } catch (ex: Exception){
            log.error("Error while get products by type: ${ex.message}", ex)

            return BaseResponse(
                data = null,
                statusCodes = HttpStatus.INTERNAL_SERVER_ERROR,
                error = ex.message
            )
        }
    }
}