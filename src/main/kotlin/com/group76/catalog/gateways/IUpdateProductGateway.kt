package com.group76.catalog.gateways

import com.group76.catalog.entities.ProductEntity

interface IUpdateProductGateway {
    fun update (
        entity: ProductEntity
    ): Boolean
}