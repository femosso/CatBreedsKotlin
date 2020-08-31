package com.example.catbreeds.data.repository

import com.example.catbreeds.data.local.CatBreedDao
import com.example.catbreeds.data.remote.CatBreedRemoteDataSource
import com.example.catbreeds.utils.performGetOperation
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val remoteDataSource: CatBreedRemoteDataSource,
    private val localDataSource: CatBreedDao
) {

    fun getBreeds() = performGetOperation(
        databaseQuery = { localDataSource.getBreeds() },
        networkCall = { remoteDataSource.getBreeds() },
        saveCallResult = { localDataSource.insertBreeds(it) }
    )

    fun getImageByBreed(breedId: String) = performGetOperation(
        databaseQuery = { localDataSource.getImageByBreed(breedId) },
        networkCall = { remoteDataSource.getImageByBreed(breedId) },
        saveCallResult = { localDataSource.insertImage(it[0]) }
    )
}