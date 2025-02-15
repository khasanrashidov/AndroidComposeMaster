package com.example.retrofitgithubviewmodel

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

data class GitHubRepo(
    val id: Int,
    val name: String,
    val description: String?,
    val html_url: String
)

interface GitHubApi {
    @GET("users/{user}/repos")
    suspend fun getUserRepos(@Path("user") user: String): Response<List<GitHubRepo>>
}


object RetrofitInstance {
    private const val BASE_URL = "https://api.github.com/"

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    val api: GitHubApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GitHubApi::class.java)
}
