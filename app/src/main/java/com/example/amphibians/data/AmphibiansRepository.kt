package com.example.amphibians.data

import com.example.amphibians.models.Amphibian
import com.example.amphibians.network.AmphibiansApiService

interface AmphibiansRepository {
    suspend fun getAmphibians() : List<Amphibian>
}

class NetworkAmphibianRepository(
    private val amphibianApiService : AmphibiansApiService
) : AmphibiansRepository {
    override suspend fun getAmphibians() : List<Amphibian> = amphibianApiService.getAmphibians()
}