package com.example.catbreeds.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breeds")
data class CatBreed(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val energyLevel: Int,
    val dogFriendly: Int,
    val healthIssues: Int,
    val wikipediaUrl: String,
    val countryCode: String
)