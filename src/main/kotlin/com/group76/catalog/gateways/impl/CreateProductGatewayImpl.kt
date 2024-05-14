package com.group76.catalog.gateways.impl

import com.group76.catalog.entities.ProductEntity
import com.group76.catalog.gateways.ICreateProductGateway
import com.group76.catalog.gateways.db.IProductRepository
import org.springframework.stereotype.Component

@Component
class CreateProductGatewayImpl(
    private val repository: IProductRepository
) : ICreateProductGateway {
    override fun insert(entity: ProductEntity): ProductEntity {
        return repository.insert(entity)
    }
}