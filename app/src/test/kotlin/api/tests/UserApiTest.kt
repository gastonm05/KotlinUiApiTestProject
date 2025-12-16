package api.tests

import api.testdata.TestData
import api.utils.ApiConfig
import api.utils.RestAssuredConfig
import io.restassured.RestAssured.given
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class UserApiTest {

    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() {
            RestAssuredConfig.configureRestAssured()
        }
    }

    @Test
    fun testGetRequest() {
        val expected = TestData.expectedUser1()

        given()
            .spec(RestAssuredConfig.getDefaultRequestSpec())
            .`when`()
            .get("${ApiConfig.USERS_ENDPOINT}/${expected.id}")
            .then()
            .statusCode(200)
            .body("username", equalTo(expected.username))
            .body("name", equalTo(expected.name))
            .body("email", containsString("@"))
    }
}
