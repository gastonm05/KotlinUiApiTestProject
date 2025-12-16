package api.clients

import api.models.User
import api.utils.ApiConfig
import api.utils.RestAssuredConfig
import io.restassured.RestAssured.given
import io.restassured.response.Response

class UserApiClient {
    
    private val requestSpec = RestAssuredConfig.getDefaultRequestSpec()
    
    fun getAllUsers(): Response {
        return given()
            .spec(requestSpec)
        .`when`()
            .get(ApiConfig.USERS_ENDPOINT)
    }
    
    fun getUserById(userId: Int): Response {
        return given()
            .spec(requestSpec)
        .`when`()
            .get("${ApiConfig.USERS_ENDPOINT}/$userId")
    }
    
    fun createUser(user: User): Response {
        return given()
            .spec(requestSpec)
            .body(user)
        .`when`()
            .post(ApiConfig.USERS_ENDPOINT)
    }
    
    fun updateUser(userId: Int, user: User): Response {
        return given()
            .spec(requestSpec)
            .body(user)
        .`when`()
            .put("${ApiConfig.USERS_ENDPOINT}/$userId")
    }
    
    fun deleteUser(userId: Int): Response {
        return given()
            .spec(requestSpec)
        .`when`()
            .delete("${ApiConfig.USERS_ENDPOINT}/$userId")
    }
}
