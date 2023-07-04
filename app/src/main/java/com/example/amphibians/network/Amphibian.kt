package com.example.amphibians.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Amphibian(
    val name : String,
    val type:String,
    val description:String,
    //To use variable names in your data class that
    // differ from the key names in the JSON response,
    // use the @SerialName annotation
    @SerialName("img_src")
    val img_src: String,
)