package com.group76.catalog.gateways.impl

import com.group76.catalog.entities.ProductEntity
import com.group76.catalog.gateways.IUpdateProductGateway
import com.group76.catalog.gateways.db.IProductRepository
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Component


@Component
class UpdateProductGateway(
    private val repository: IProductRepository
) : IUpdateProductGateway {
    override fun update(entity: ProductEntity): Boolean {
        val response = repository.update(Query()
            .addCriteria(
                Criteria.where("_id").`is`(entity.id)
            ), Update()
                .set("name", entity.name)
                .set("description", entity.description)
                .set("price", entity.price)
                .set("image", entity.image)
                .set("type", entity.type)
        )

        return response.wasAcknowledged()
    }
}