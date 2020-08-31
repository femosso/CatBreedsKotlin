package com.example.catbreeds.data.remote

import com.example.catbreeds.data.entities.CatBreed
import com.example.catbreeds.data.entities.CatBreedImage
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/v1/breeds")
    suspend fun getBreeds(): Response<List<CatBreed>>

    @GET("/v1/images/search")
    suspend fun getImageByBreed(@Query("breed_id") breedId: String): Response<List<CatBreedImage>>
}