package com.example.amphibians.data

import com.example.amphibians.network.AmphibianApiService
import com.example.amphibians.network.AmphibianPhoto

interface AmphibianPhotoRepository {
    //fetch list of MarsPhoto from API
    suspend fun getAmphibianDetails(): List<AmphibianPhoto>
}

class NetworkAmphibianPhotoRepository(
    private val amphibianApiService: AmphibianApiService
) : AmphibianPhotoRepository {
    override suspend fun getAmphibianDetails(): List<AmphibianPhoto> =
        amphibianApiService.getAmphibians()
}
