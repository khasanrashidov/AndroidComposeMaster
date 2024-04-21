package com.example.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HEAD
import retrofit2.http.OPTIONS
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ReqResApi {
    @GET("api/users")
    suspend fun getUsers(): Response<UsersResponse>
    @POST("api/users")
    suspend fun createUser(@Body user: User): Response<User>
    @PUT("api/users/{id}")
    suspend fun updateUser(@Path("id") id: Int, @Body user: User): Response<User>
    @DELETE("api/users/{id}")
    suspend fun deleteUser(@Path("id") id: Int): Response<Unit>
    @PATCH("api/users/{id}")
    suspend fun updateUserPartial(@Path("id") id: Int, @Body user: User): Response<User>
    @HEAD("api/users/{id}")
    suspend fun getUserHeaders(@Path("id") id: Int): Response<Void>
    @OPTIONS("api/users/{id}")
    suspend fun getUserOptions(@Path("id") id: Int): Response<Void>
}

data class UsersResponse(val data: List<User>)
data class User(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
)


object RetrofitInstance {
    private const val BASE_URL = "https://reqres.in/"

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    val api: ReqResApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ReqResApi::class.java)

}
