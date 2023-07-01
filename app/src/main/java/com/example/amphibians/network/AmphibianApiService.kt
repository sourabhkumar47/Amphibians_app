package com.example.amphibians.network

import retrofit2.http.GET

//Define how retrofit talk to web server
interface AmphibianApiService{
    @GET("photos")
    suspend fun getAmphibians(): List<AmphibianPhoto>
}