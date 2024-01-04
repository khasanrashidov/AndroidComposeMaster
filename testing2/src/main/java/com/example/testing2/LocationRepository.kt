package com.example.testing2

interface LocationRepository {
    suspend fun getLocation(): String
}
