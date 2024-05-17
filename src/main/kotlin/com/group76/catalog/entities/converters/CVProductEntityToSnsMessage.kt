package com.group76.catalog.entities.converters

import com.group76.catalog.entities.ProductEntity
import com.group76.catalog.entities.enum.ProductOperation
import com.group76.catalog.entities.request.ProductMessageSns
import org.springframework.stereotype.Component

@Component
class CVProductEntityToSnsMessage {
    fun convert(source: ProductEntity, operation: ProductOperation): ProductMessageSns {
        return ProductMessageSns(
            name = source.name,
            type = source.type,
            price = source.price,
            id = source.id!!,
            operation = operation,
            description = source.description
        )
    }

    fun convertToDeleteMessage(id: String): ProductMessageSns {
        return ProductMessageSns(
            name = null,
            type = null,
            price = null,
            id = id,
            operation = ProductOperation.DELETED,
            description = null
        )
    }
}