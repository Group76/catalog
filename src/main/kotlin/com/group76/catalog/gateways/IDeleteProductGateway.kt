package com.group76.catalog.gateways

interface IDeleteProductGateway {
    fun delete (
        id: String
    ): Boolean
}