package com.group76.catalog.controller

import com.group76.catalog.mapping.UrlMapping
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(UrlMapping.Version.V1 + UrlMapping.Resource.PRODUCT)
class ProductController() {
    val logger: Logger = LoggerFactory.getLogger(LoggerFactory::class.java)

    @GetMapping()
    fun get(): ResponseEntity<Any> {
        logger.info("New Request")
        return ResponseEntity.ok("TEST OK")
    }
}