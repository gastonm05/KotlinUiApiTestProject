package api.tests

import api.clients.PostApiClient
import api.models.Post
import api.utils.RestAssuredConfig
import api.utils.ResponseValidator
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class PostApiTests {
    
    companion object {
        private lateinit var postApiClient: PostApiClient
        
        @JvmStatic
        @BeforeAll
        fun setup() {
                        RestAssuredConfig.configureRestAssured()
            postApiClient = PostApiClient()
        }
    }
    
    @Test
    fun testGetAllPosts() {
        val response = postApiClient.getAllPosts()
        
        ResponseValidator.validateStatusCode(response, 200)
        ResponseValidator.validateContentType(response)
        ResponseValidator.validateNotEmpty(response)
        
        val posts = response.jsonPath().getList<Post>("", Post::class.java)
        assertTrue(posts.isNotEmpty(), "Posts list should not be empty")
    }
    
    @Test
    fun testGetPostById() {
        val postId = 1
        val response = postApiClient.getPostById(postId)
        
        ResponseValidator.validateStatusCode(response, 200)
        
        val post = response.`as`(Post::class.java)
        assertEquals(postId, post.id)
        assertNotNull(post.title)
        assertNotNull(post.body)
    }
    
    @Test
    fun testGetPostsByUserId() {
        val userId = 1
        val response = postApiClient.getPostsByUserId(userId)
        
        ResponseValidator.validateStatusCode(response, 200)
        
        val posts = response.jsonPath().getList<Post>("", Post::class.java)
        assertTrue(posts.isNotEmpty())
        posts.forEach { post ->
            assertEquals(userId, post.userId, "All posts should belong to user $userId")
        }
    }
    
    @Test
    fun testCreatePost() {
        val newPost = Post(
            userId = 1,
            title = "Test Post Title",
            body = "This is a test post body"
        )
        
        val response = postApiClient.createPost(newPost)
        
        ResponseValidator.validateStatusCode(response, 201)
        
        val createdPost = response.`as`(Post::class.java)
        assertNotNull(createdPost.id)
        assertEquals(newPost.title, createdPost.title)
        assertEquals(newPost.body, createdPost.body)
    }
}
