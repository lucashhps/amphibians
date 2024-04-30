package com.example.amphibians.network

import com.example.amphibians.models.Amphibian
import retrofit2.http.GET

interface AmphibiansApiService {
    @GET("https://android-kotlin-fun-mars-server.appspot.com/amphibians")
    suspend fun getAmphibians() : List<Amphibian>
}