package api.tests

import api.clients.UserApiClient
import api.models.User
import api.testdata.TestData
import api.utils.RestAssuredConfig
import api.utils.ResponseValidator
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class UserApiTests {
    
    companion object {
        private lateinit var userApiClient: UserApiClient
        
        @JvmStatic
        @BeforeAll
        fun setup() {
                        RestAssuredConfig.configureRestAssured()
            userApiClient = UserApiClient()
        }
    }
    
    @Test
    fun testGetAllUsers() {
        val response = userApiClient.getAllUsers()
        
        ResponseValidator.validateStatusCode(response, 200)
        ResponseValidator.validateContentType(response)
        ResponseValidator.validateNotEmpty(response)
        
        val users = response.jsonPath().getList<User>("", User::class.java)
        assertTrue(users.isNotEmpty(), "Users list should not be empty")
    }
    
    @Test
    fun testGetUserById() {
        val userId = 1
        val response = userApiClient.getUserById(userId)
        
        ResponseValidator.validateStatusCode(response, 200)
        ResponseValidator.validateContentType(response)
        
        val user = response.`as`(User::class.java)
        assertEquals(userId, user.id)
        assertNotNull(user.name)
        assertNotNull(user.email)
    }
    
    @Test
    fun testCreateUser() {
        val newUser = TestData.sampleUser()
        
        val response = userApiClient.createUser(newUser)
        
        ResponseValidator.validateStatusCode(response, 201)
        
        val createdUser = response.`as`(User::class.java)
        assertNotNull(createdUser.id)
        assertEquals(newUser.name, createdUser.name)
        assertEquals(newUser.email, createdUser.email)
    }
    
    @Test
    fun testUpdateUser() {
        val userId = 1
        val updatedUser = TestData.updatedUser(id = userId)
        
        val response = userApiClient.updateUser(userId, updatedUser)
        
        ResponseValidator.validateStatusCode(response, 200)
        
        val user = response.`as`(User::class.java)
        assertEquals(updatedUser.name, user.name)
        assertEquals(updatedUser.email, user.email)
    }
    
    @Test
    fun testDeleteUser() {
        val userId = 1
        val response = userApiClient.deleteUser(userId)
        
        ResponseValidator.validateStatusCode(response, 200)
    }
}
