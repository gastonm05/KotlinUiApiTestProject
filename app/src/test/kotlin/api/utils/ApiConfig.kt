package api.utils

object ApiConfig {
    const val BASE_URL = "https://jsonplaceholder.typicode.com"
    const val USERS_ENDPOINT = "/users"
    const val POSTS_ENDPOINT = "/posts"
    const val COMMENTS_ENDPOINT = "/comments"
    const val ALBUMS_ENDPOINT = "/albums"
    const val PHOTOS_ENDPOINT = "/photos"
    const val TODOS_ENDPOINT = "/todos"
    
    // Common headers
    const val CONTENT_TYPE = "Content-Type"
    const val CONTENT_TYPE_JSON = "application/json"
    
    // Timeouts
    const val DEFAULT_TIMEOUT = 10000
    const val CONNECT_TIMEOUT = 5000
}
