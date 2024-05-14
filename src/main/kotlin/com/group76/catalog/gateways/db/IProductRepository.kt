package com.group76.catalog.gateways.db

import com.group76.catalog.entities.ProductEntity
import com.mongodb.client.result.UpdateResult
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update

interface IProductRepository {
    fun findById(
        id: String
    ) : ProductEntity?

    fun findByFilter(
        query: Query,
        pageable: Pageable,
        sort: Sort
    ) : Page<ProductEntity>

    fun insert(
        entity: ProductEntity
    ): ProductEntity

    fun update(
        query: Query,
        update: Update
    ): UpdateResult

    fun exists(
        query: Query
    ): Boolean
}