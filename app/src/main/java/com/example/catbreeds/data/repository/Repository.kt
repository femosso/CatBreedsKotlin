package com.example.catbreeds.data.repository

import androidx.lifecycle.liveData
import com.example.catbreeds.data.remote.CatBreedRemoteDataSource
import com.example.catbreeds.utils.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: CatBreedRemoteDataSource
) {

    fun getBreeds() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = remoteDataSource.getBreeds()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getImageByBreed(breedId: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = remoteDataSource.getImageByBreed(breedId)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}