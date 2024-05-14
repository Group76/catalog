package com.group76.catalog.gateways.impl

import com.group76.catalog.entities.ProductEntity
import com.group76.catalog.entities.enum.ProductType
import com.group76.catalog.gateways.IReadProductGateway
import com.group76.catalog.gateways.db.IProductRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Component

@Component
class ReadProductGatewayImpl(
    private val repository: IProductRepository
) : IReadProductGateway {
    override fun findById(id: String): ProductEntity? {
        return repository.findById(id)
    }

    override fun findByType(
        type: ProductType,
        pageable: Pageable
    ): Page<ProductEntity> {
        val query = Query()
            .addCriteria(
                Criteria.where("type").`is`(type)
            )

        return repository.findByFilter(
            query = query,
            pageable = pageable,
            sort = Sort.by(
                Sort.Direction.ASC,
                "name"
            )
        )
    }

    override fun nameExists(name: String): Boolean {
        TODO("Not yet implemented")
    }
}