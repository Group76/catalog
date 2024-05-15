package com.group76.catalog.entities.converters

import com.group76.catalog.entities.ProductEntity
import com.group76.catalog.entities.response.GetProductResponse
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class CVProductEntityToGetProductResponse: Converter<ProductEntity, GetProductResponse> {
    override fun convert(source: ProductEntity): GetProductResponse {
        return GetProductResponse(
            name = source.name,
            type = source.type,
            image = source.image,
            price = source.price,
            id = source.id!!
        )
    }
}