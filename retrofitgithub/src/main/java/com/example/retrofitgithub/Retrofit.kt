package com.example.retrofitgithub

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
import java.security.acl.Owner

data class GitHubRepo(
    val id: Int,
    val name: String,
    val description: String?,
    val html_url: String,
    val full_name: String,
    val private: Boolean,
    val updated_at: String,
    val size: Int,
)

interface GitHubApi {
    @GET("users/{user}/repos")
    suspend fun getUserRepos(@Path("user") user: String): Response<List<GitHubRepo>>
    @POST("users/{user}/repos")
    suspend fun createRepo(@Path("user") user: String, @Body repo: GitHubRepo): Response<GitHubRepo>
    @PUT("repos/{owner}/{repo}")
//    suspend fun updateRepo(@Path("owner") owner: String, @Path("repo") repo: String, @Body repo: GitHubRepo): Response<GitHubRepo>
//    @DELETE("repos/{owner}/{repo}")
    suspend fun deleteRepo(@Path("owner") owner: String, @Path("repo") repo: String): Response<Unit>
//    @PATCH("repos/{owner}/{repo}")
//    suspend fun updateRepoPartial(@Path("owner") owner: String, @Path("repo") repo: String, @Body repo: GitHubRepo): Response<GitHubRepo>
//    @HEAD("repos/{owner}/{repo}")
//    suspend fun getRepoHeaders(@Path("owner") owner: String, @Path("repo") repo: String): Response<Void>
    @OPTIONS("repos/{owner}/{repo}")
    suspend fun getRepoOptions(@Path("owner") owner: String, @Path("repo") repo: String): Response<Void>
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
