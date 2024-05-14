package com.group76.catalog.gateways

import com.group76.catalog.entities.ProductEntity
import com.group76.catalog.entities.enum.ProductType
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface IReadProductGateway {
    fun findById(
        id: String
    ): ProductEntity?

    fun findByType(
       type: ProductType,
       pageable: Pageable
    ): Page<ProductEntity>

    fun nameExists(
        name: String
    ): Boolean
}