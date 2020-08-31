package com.example.catbreeds.data.remote

import javax.inject.Inject

class CatBreedRemoteDataSource @Inject constructor(
    private val apiInterface: ApiInterface
) : BaseDataSource() {

    suspend fun getBreeds() = getResult { apiInterface.getBreeds() }
    suspend fun getImageByBreed(breedId: String) =
        getResult { apiInterface.getImageByBreed(breedId) }
}