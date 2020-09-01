package com.example.catbreeds.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.catbreeds.data.entities.CatBreedImage
import com.example.catbreeds.data.repository.Repository
import com.example.catbreeds.utils.Resource

class CatBreedDetailsViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _id = MutableLiveData<String>()

    private val _catBreedImage = _id.switchMap { id ->
        repository.getImageByBreed(id)
    }
    val catBreedImage: LiveData<Resource<Resource<List<CatBreedImage>>>> = _catBreedImage

    fun start(id: String) {
        _id.value = id
    }
}
