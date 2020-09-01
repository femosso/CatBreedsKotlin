package com.example.catbreeds.data.entities

import com.google.gson.annotations.SerializedName

data class CatBreed(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("energy_level") val energyLevel: Int,
    @SerializedName("dog_friendly") val dogFriendly: Int,
    @SerializedName("health_issues") val healthIssues: Int,
    @SerializedName("wikipedia_url") val wikipediaUrl: String,
    @SerializedName("country_code") val countryCode: String,
    @SerializedName("origin") val origin: String
)