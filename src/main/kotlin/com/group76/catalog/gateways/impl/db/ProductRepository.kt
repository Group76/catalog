package com.group76.catalog.gateways.impl.db

import com.group76.catalog.entities.ProductEntity
import com.group76.catalog.gateways.db.IProductRepository
import com.mongodb.client.result.UpdateResult
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.retry.support.RetryTemplate
import org.springframework.stereotype.Repository

@Repository
class ProductRepository (
    private val mongoTemplate: MongoTemplate,
    private val retryTemplate: RetryTemplate
): IProductRepository {
    private val log = LoggerFactory.getLogger(ProductRepository::class.java)
    override fun findById(id: String): ProductEntity? {
        try {
            return retryTemplate.execute<ProductEntity?, Exception> {
                mongoTemplate.findById(id, ProductEntity::class.java)
            }
        } catch (ex: Exception){
            log.error("Error in findById: ${ex.message}", ex)
            throw ex
        }
    }

    override fun findByFilter(query: Query, pageable: Pageable, sort: Sort): Page<ProductEntity> {
        try {
            val totalCount: Long = mongoTemplate.count(query, ProductEntity::class.java)

            query.with(pageable)
            query.with(sort)

            val result = retryTemplate.execute<List<ProductEntity>, Exception> {
                mongoTemplate.find(query, ProductEntity::class.java)
            }

            return PageImpl(result, pageable, totalCount)
        } catch (ex: Exception) {
            log.error("Error in findByFilter: ${ex.message}", ex)
            throw ex
        }
    }

    override fun insert(entity: ProductEntity): ProductEntity {
        try {
            return retryTemplate.execute<ProductEntity, Exception> {
                mongoTemplate.save(entity)
            }
        } catch (ex: Exception){
            log.error("Error in insert: ${ex.message}", ex)
            throw ex
        }
    }

    override fun update(query: Query, update: Update): UpdateResult {
        try {
            return retryTemplate.execute<UpdateResult, Exception> {
                mongoTemplate.updateFirst(query, update, ProductEntity::class.java)
            }
        } catch (ex: Exception){
            log.error("Error in update: ${ex.message}", ex)
            throw ex
        }
    }

    override fun exists(query: Query): Boolean {
        try {
            return retryTemplate.execute<Boolean, Exception> {
                mongoTemplate.exists(query, ProductEntity::class.java)
            }
        } catch (ex: Exception){
            log.error("Error in exists: ${ex.message}", ex)
            throw ex
        }
    }

}