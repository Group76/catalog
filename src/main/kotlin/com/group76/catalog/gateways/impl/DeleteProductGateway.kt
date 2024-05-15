package com.group76.catalog.gateways.impl

import com.group76.catalog.gateways.IDeleteProductGateway
import com.group76.catalog.gateways.db.IProductRepository
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update


class DeleteProductGateway(
    private val repository: IProductRepository
) : IDeleteProductGateway {
    override fun delete(id: String): Boolean {
        val response = repository.update(Query()
            .addCriteria(
                Criteria.where("_id").`is`(id)
            ), Update().set("deleted", true))

        return response.wasAcknowledged()
    }
}