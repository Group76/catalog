package com.group76.catalog.entities.converters

import com.group76.catalog.entities.ProductEntity
import com.group76.catalog.entities.request.CreateProductRequest
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class CVCreateRequestToProductEntity: Converter<CreateProductRequest, ProductEntity> {
    override fun convert(source: CreateProductRequest): ProductEntity {
        return ProductEntity(
            description = source.description,
            name = source.name,
            type = source.type,
            image = source.image,
            price = source.price,
            deleted = false
        )
    }
}