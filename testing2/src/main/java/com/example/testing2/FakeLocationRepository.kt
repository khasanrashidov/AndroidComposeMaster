package com.example.testing2

class FakeLocationRepository : LocationRepository {
    override suspend fun getLocation(): String {
        return "51.5074, -0.1278" // Fake location coordinates (London)
    }
}
