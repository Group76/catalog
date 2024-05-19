package com.group76.catalog.usecases

import com.group76.catalog.configuration.SystemProperties
import com.group76.catalog.entities.converters.CVCreateRequestToProductEntity
import com.group76.catalog.entities.converters.CVProductEntityToGetProductResponse
import com.group76.catalog.entities.converters.CVProductEntityToSnsMessage
import com.group76.catalog.gateways.ICreateProductGateway
import com.group76.catalog.gateways.IReadProductGateway
import com.group76.catalog.service.ISnsService
import com.group76.catalog.usecases.impl.CreateProductUseCaseImpl
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CreateProductUseCaseTests {
    @Mock
    private lateinit var createGateway: ICreateProductGateway

    @Mock
    private lateinit var  readGateway: IReadProductGateway

    @Mock
    private lateinit var  cvCreateRequestToProductEntity: CVCreateRequestToProductEntity

    @Mock
    private lateinit var  cvProductEntityToGetProductResponse: CVProductEntityToGetProductResponse

    @Mock
    private lateinit var snsService: ISnsService

    @Mock
    private lateinit var cvProductEntityToSnsMessage: CVProductEntityToSnsMessage

    @Mock
    private lateinit var systemProperties : SystemProperties

    private lateinit var createProductUseCaseImpl: CreateProductUseCaseImpl
    private var closeable: AutoCloseable? = null

    @BeforeEach
    fun setup(){
        closeable = MockitoAnnotations.openMocks(this)
        createProductUseCaseImpl = CreateProductUseCaseImpl(
            createGateway,
            readGateway,
            cvCreateRequestToProductEntity,
            cvProductEntityToGetProductResponse,
            snsService,
            cvProductEntityToSnsMessage,
            systemProperties
        )
    }

    @AfterEach
    fun close(){
        closeable?.close()
    }

    @Test
    fun`when`(){
        Assertions.assertTrue(true)
    }
}