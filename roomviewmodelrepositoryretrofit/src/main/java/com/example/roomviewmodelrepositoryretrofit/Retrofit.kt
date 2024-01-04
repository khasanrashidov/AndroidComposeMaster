package com.example.roomviewmodelrepositoryretrofit

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

data class UserResponse(
    val id: Int,
    val first_name: String,
    val last_name: String
)


interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<List<UserResponse>>

    @POST("users")
    suspend fun addUser(@Body user: UserResponse): Response<UserResponse>

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: Int): Response<Unit>
}


fun provideRetrofit(): ApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl("htts://reqres.in")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(ApiService::class.java)
}
