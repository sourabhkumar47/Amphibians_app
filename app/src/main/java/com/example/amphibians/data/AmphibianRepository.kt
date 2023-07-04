package com.example.amphibians.data

import com.example.amphibians.network.AmphibianApiService
import com.example.amphibians.network.Amphibians

interface AmphibianRepository {
    //fetch list of MarsPhoto from API
    suspend fun getAmphibianDetails(): List<Amphibians>
}

class NetworkAmphibianRepository(
    private val amphibianApiService: AmphibianApiService
) : AmphibianRepository {
    override suspend fun getAmphibianDetails(): List<Amphibians> =
        amphibianApiService.getAmphibians()
}
