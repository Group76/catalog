package com.group76.catalog.entities.converters

import com.group76.catalog.entities.ProductEntity
import com.group76.catalog.entities.request.UpdateProductRequest
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class CVUpdateRequestToProductEntity: Converter<UpdateProductRequest, ProductEntity> {
    override fun convert(source: UpdateProductRequest): ProductEntity {
        return ProductEntity(
            description = source.description,
            name = source.name,
            type = source.type,
            image = source.image,
            price = source.price,
            id = source.id,
            deleted = false
        )
    }
}