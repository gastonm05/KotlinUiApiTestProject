package api.clients

import api.models.Post
import api.utils.ApiConfig
import api.utils.RestAssuredConfig
import io.restassured.RestAssured.given
import io.restassured.response.Response

class PostApiClient {
    
    private val requestSpec = RestAssuredConfig.getDefaultRequestSpec()
    
    fun getAllPosts(): Response {
        return given()
            .spec(requestSpec)
        .`when`()
            .get(ApiConfig.POSTS_ENDPOINT)
    }
    
    fun getPostById(postId: Int): Response {
        return given()
            .spec(requestSpec)
        .`when`()
            .get("${ApiConfig.POSTS_ENDPOINT}/$postId")
    }
    
    fun getPostsByUserId(userId: Int): Response {
        return given()
            .spec(requestSpec)
            .queryParam("userId", userId)
        .`when`()
            .get(ApiConfig.POSTS_ENDPOINT)
    }
    
    fun createPost(post: Post): Response {
        return given()
            .spec(requestSpec)
            .body(post)
        .`when`()
            .post(ApiConfig.POSTS_ENDPOINT)
    }
    
    fun updatePost(postId: Int, post: Post): Response {
        return given()
            .spec(requestSpec)
            .body(post)
        .`when`()
            .put("${ApiConfig.POSTS_ENDPOINT}/$postId")
    }
    
    fun deletePost(postId: Int): Response {
        return given()
            .spec(requestSpec)
        .`when`()
            .delete("${ApiConfig.POSTS_ENDPOINT}/$postId")
    }
}
