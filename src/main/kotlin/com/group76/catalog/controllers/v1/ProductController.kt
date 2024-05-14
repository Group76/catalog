package com.group76.catalog.controllers.v1

import com.group76.catalog.entities.enum.ProductType
import com.group76.catalog.entities.request.CreateProductRequest
import com.group76.catalog.entities.request.UpdateProductRequest
import com.group76.catalog.entities.response.GetProductResponse
import com.group76.catalog.mapping.UrlMapping
import com.group76.catalog.usecases.*
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(UrlMapping.Version.V1 + UrlMapping.Resource.PRODUCT)
class ProductController(
    private val createUseCase: ICreateProductUseCase,
    private val deleteUseCase: IDeleteProductUseCase,
    private val getByIdUseCase: IGetByIdProductUseCase,
    private val getByTypeUseCase: IGetByTypeProductUseCase,
    private val updateUseCase: IUpdateProductUseCase
) {
    val logger: Logger = LoggerFactory.getLogger(LoggerFactory::class.java)

    @PostMapping(
        name = "CreateProduct"
    )
    @Operation(
        method = "CreateProduct",
        description = "Create a product",
        responses = [
            ApiResponse(
                description = "OK", responseCode = "200", content = [
                    Content(schema = Schema(implementation = GetProductResponse::class))
                ]
            ),
            ApiResponse(
                description = "Bad Request", responseCode = "400", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Internal Error", responseCode = "500", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            )
        ]
    )
    fun createProduct(
        @Valid @RequestBody request: CreateProductRequest
    ): ResponseEntity<GetProductResponse> {
        return createUseCase.execute(request)
    }

    @GetMapping(
        path = ["type"],
        name = "GetByType"
    )
    @Operation(
        method = "GetProductByType",
        description = "Get a list of products by type",
        responses = [
            ApiResponse(
                description = "OK", responseCode = "200", content = [
                    Content(schema = Schema(implementation = GetProductResponse::class))
                ]
            ),
            ApiResponse(
                description = "Bad Request", responseCode = "400", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Internal Error", responseCode = "500", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            )
        ]
    )
    fun getByType(
        pageable: Pageable,
        @PathVariable type: ProductType
    ): ResponseEntity<Page<GetProductResponse>> {
//        return productService.getProducts(pageable)
    }

    @GetMapping(
        name = "GetById"
    )
    @Operation(
        method = "GetProductById",
        description = "Get a product by type",
        responses = [
            ApiResponse(
                description = "OK", responseCode = "200", content = [
                    Content(schema = Schema(implementation = GetProductResponse::class))
                ]
            ),
            ApiResponse(
                description = "Bad Request", responseCode = "400", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Internal Error", responseCode = "500", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            )
        ]
    )
    fun getById(
        @PathVariable id: String
    ): ResponseEntity<GetProductResponse> {
//        return productService.getProducts(pageable)
    }

    @PutMapping(
        name = "UpdateProduct"
    )
    @Operation(
        method = "UpdateProduct",
        description = "Update a product",
        responses = [
            ApiResponse(
                description = "OK", responseCode = "200", content = [
                    Content(schema = Schema(implementation = GetProductResponse::class))
                ]
            ),
            ApiResponse(
                description = "Bad Request", responseCode = "400", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Internal Error", responseCode = "500", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            )
        ]
    )
    fun updateProduct(
        @Valid @RequestBody request: UpdateProductRequest
    ): ResponseEntity<GetProductResponse> {
//        return productService.getProducts(pageable)
    }

    @DeleteMapping(
        name = "DeleteProduct"
    )
    @Operation(
        method = "DeleteProduct",
        description = "Delete a product by id",
        responses = [
            ApiResponse(
                description = "OK", responseCode = "200", content = [
                    Content(schema = Schema(implementation = GetProductResponse::class))
                ]
            ),
            ApiResponse(
                description = "Bad Request", responseCode = "400", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Internal Error", responseCode = "500", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            )
        ]
    )
    fun deleteProduct(
        @PathVariable id: String
    ): ResponseEntity<Any> {
//        return productService.getProducts(pageable)
    }
}