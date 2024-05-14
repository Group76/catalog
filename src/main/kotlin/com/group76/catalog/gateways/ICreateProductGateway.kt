package com.group76.catalog.gateways

import com.group76.catalog.entities.ProductEntity

interface ICreateProductGateway {
    fun insert (
        entity: ProductEntity
    ): ProductEntity
}