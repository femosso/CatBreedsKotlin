package com.example.catbreeds.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breeds_image")
data class CatBreedImage(
    @PrimaryKey
    val url: String
)