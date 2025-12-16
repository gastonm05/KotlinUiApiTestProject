package api.utils

import io.restassured.RestAssured
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.restassured.builder.RequestSpecBuilder
import io.restassured.filter.log.LogDetail
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification

object RestAssuredConfig {
    
        private val objectMapper: ObjectMapper = ObjectMapper().registerKotlinModule()
    
    fun getDefaultRequestSpec(): RequestSpecification {
        return RequestSpecBuilder()
            .setBaseUri(ApiConfig.BASE_URL)
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .log(LogDetail.URI)
            .log(LogDetail.METHOD)
            .build()
    }
    
    fun configureRestAssured() {
        RestAssured.baseURI = ApiConfig.BASE_URL
                RestAssured.config = RestAssured.config()
                    .objectMapperConfig(
                        io.restassured.config.ObjectMapperConfig()
                            .jackson2ObjectMapperFactory { _, _ -> objectMapper }
                    )
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()
    }
}
