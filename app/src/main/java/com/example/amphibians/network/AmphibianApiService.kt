package com.example.amphibians.network

import retrofit2.http.GET

//Define how retrofit talk to web server
interface AmphibianApiService{
    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibians>
}