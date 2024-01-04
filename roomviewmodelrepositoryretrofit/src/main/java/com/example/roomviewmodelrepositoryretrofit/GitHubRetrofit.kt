package com.example.roomviewmodelrepositoryretrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

data class GithubUser(
    val login: String,
    val id: Int,
    val avatar_url: String,
    val name: String?,
    val company: String?,
    val blog: String?,
    val location: String?,
    val email: String?,
    val bio: String?
)


interface GithubApi {
    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): GithubUser
}


fun provideGithubApi(): GithubApi {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(GithubApi::class.java)
}
