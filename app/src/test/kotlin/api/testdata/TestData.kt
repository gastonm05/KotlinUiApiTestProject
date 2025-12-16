package api.testdata

import api.models.Post
import api.models.User

object TestData {
    fun sampleUser(
        id: Int = 0,
        name: String = "Test User",
        username: String = "testuser",
        email: String = "test@example.com"
    ): User = User(
        id = id,
        name = name,
        username = username,
        email = email
    )

    fun updatedUser(
        id: Int = 1,
        name: String = "Updated User",
        username: String = "updateduser",
        email: String = "updated@example.com"
    ): User = User(
        id = id,
        name = name,
        username = username,
        email = email
    )

    fun samplePost(
        userId: Int = 1,
        title: String = "Test Post Title",
        body: String = "This is a test post body"
    ): Post = Post(
        userId = userId,
        title = title,
        body = body
    )

    fun expectedUser1(): User = User(
        id = 1,
        name = "Leanne Graham",
        username = "Bret",
        email = "Sincere@april.biz"
    )
}
