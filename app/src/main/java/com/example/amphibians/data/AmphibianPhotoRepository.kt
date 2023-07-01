package com.example.amphibians.data

import com.example.amphibians.network.AmphibianApiService
import com.example.amphibians.network.Amphibian

interface AmphibianPhotoRepository {
    //fetch list of MarsPhoto from API
    suspend fun getAmphibianDetails(): List<Amphibian>
}

class NetworkAmphibianPhotoRepository(
    private val amphibianApiService: AmphibianApiService
) : AmphibianPhotoRepository {
    override suspend fun getAmphibianDetails(): List<Amphibian> =
        amphibianApiService.getAmphibians()
}
