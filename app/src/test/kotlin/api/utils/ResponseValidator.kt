package api.utils

import io.restassured.response.Response
import org.junit.jupiter.api.Assertions.*

object ResponseValidator {
    
    fun validateStatusCode(response: Response, expectedStatusCode: Int) {
        assertEquals(
            expectedStatusCode, 
            response.statusCode,
            "Expected status code $expectedStatusCode but got ${response.statusCode}"
        )
    }
    
    fun validateContentType(response: Response, expectedContentType: String = "application/json") {
        assertTrue(
            response.contentType.contains(expectedContentType, ignoreCase = true),
            "Expected content type to contain $expectedContentType but got ${response.contentType}"
        )
    }
    
    fun validateResponseTime(response: Response, maxResponseTimeMs: Long = 2000) {
        assertTrue(
            response.time < maxResponseTimeMs,
            "Response time ${response.time}ms exceeded maximum $maxResponseTimeMs ms"
        )
    }
    
    fun validateNotEmpty(response: Response) {
        assertNotNull(response.body, "Response body is null")
        assertTrue(
            response.body.asString().isNotEmpty(),
            "Response body is empty"
        )
    }
}
