package com.example.catbreeds.data.model

import com.google.gson.annotations.SerializedName

data class CatBreedImage(
    @SerializedName("breeds") val breeds: List<CatBreed> = emptyList(),
    @SerializedName("url") val url: String = ""
)