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
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
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
@RequestMapping(UrlMapping.Version.V1 + UrlMapping.Resource.PRODUCT_TEST)
class ProductTestController(
    private val createUseCase: ICreateProductUseCase
) {
    val logger: Logger = LoggerFactory.getLogger(LoggerFactory::class.java)

    val requestMain = CreateProductRequest(
        description = "Test Main",
        type = ProductType.MAIN,
        image = null,
        name = "Test Main",
        price = 1.0
    )

    val requestSide = CreateProductRequest(
        description = "Test Side",
        type = ProductType.SIDE,
        image = null,
        name = "Test Side",
        price = 1.0
    )

    val requestDessert = CreateProductRequest(
        description = "Test Dessert",
        type = ProductType.DESSERT,
        image = null,
        name = "Test Dessert",
        price = 1.0
    )

    val requestBeverage = CreateProductRequest(
        description = "Test Beverage",
        type = ProductType.BEVERAGE,
        image = null,
        name = "Test Beverage",
        price = 1.0
    )

    @PostMapping(
        name = "CreateTestProduct"
    )
    @Operation(
        hidden = true,
        method = "CreateTestProduct",
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
    ): ResponseEntity<Any> {
        var response = createUseCase.execute(requestMain)

        if(response.error != null && response.statusCodes != HttpStatus.BAD_REQUEST)
            return ResponseEntity(
                response.error,
                response.statusCodes
            )

        response = createUseCase.execute(requestSide)

        if(response.error != null && response.statusCodes != HttpStatus.BAD_REQUEST)
            return ResponseEntity(
                response.error,
                response.statusCodes
            )


        response = createUseCase.execute(requestBeverage)

        if(response.error != null && response.statusCodes != HttpStatus.BAD_REQUEST)
            return ResponseEntity(
                response.error,
                response.statusCodes
            )

        response = createUseCase.execute(requestDessert)

        if(response.error != null && response.statusCodes != HttpStatus.BAD_REQUEST)
            return ResponseEntity(
                response.error,
                response.statusCodes
            )

        return ResponseEntity.ok().build()
    }
}