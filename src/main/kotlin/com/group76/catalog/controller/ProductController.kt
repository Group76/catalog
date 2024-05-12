package com.group76.catalog.controller

import com.group76.catalog.UrlMapping.UrlMapping
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(UrlMapping.Version.V1 + UrlMapping.Resource.PRODUCT)
class ProductController() {
    @GetMapping()
    fun get(): ResponseEntity<Any> {
        return ResponseEntity.ok("TEST OK")
    }
}