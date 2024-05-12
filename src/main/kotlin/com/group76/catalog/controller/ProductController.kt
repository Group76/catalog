package com.group76.catalog.controller

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product")
class ProductController() {

    @GetMapping()
    fun get(): ResponseEntity<Any> {

        return ResponseEntity.ok("TEST")
    }
}