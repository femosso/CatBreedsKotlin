package com.example.catbreeds.data.model

import com.google.gson.annotations.SerializedName

data class CatBreed(
    @SerializedName("id") val id: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("energy_level") val energyLevel: Int = 0,
    @SerializedName("dog_friendly") val dogFriendly: Int = 0,
    @SerializedName("health_issues") val healthIssues: Int = 0,
    @SerializedName("wikipedia_url") val wikipediaUrl: String = "",
    @SerializedName("country_code") val countryCode: String = "",
    @SerializedName("origin") val origin: String = ""
)