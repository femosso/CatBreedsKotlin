package com.example.catbreeds.data.repository

import androidx.lifecycle.liveData
import com.example.catbreeds.data.repository.remote.CatBreedRemoteDataSource
import com.example.catbreeds.utils.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: CatBreedRemoteDataSource
) {

    fun getBreeds() = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        emit(remoteDataSource.getBreeds())
    }

    fun getImageByBreed(breedId: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        emit(remoteDataSource.getImageByBreed(breedId))
    }
}