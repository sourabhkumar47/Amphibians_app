package com.example.amphibians.network

//import com.example.amphibians.network.Amphibian
import retrofit2.http.GET

//Define how retrofit talk to web server
interface AmphibiansApiService {
    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibian>
}