package api.tests

import io.restassured.RestAssured
import io.restassured.RestAssured.given
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test

class UserApiTest {
    @Test
    fun testGetRequest() {
        given()
            .baseUri("https://jsonplaceholder.typicode.com")
            .`when`()
            .get("/users/1")
            .then()
            .statusCode(200)
            .body("username", org.hamcrest.Matchers.equalTo("Bret"))
            .body("name", org.hamcrest.Matchers.notNullValue())
            .body("email", org.hamcrest.Matchers.containsString("@"))
    }
}
