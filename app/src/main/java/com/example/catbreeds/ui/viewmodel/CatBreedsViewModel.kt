package com.example.catbreeds.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.catbreeds.data.entities.CatBreed
import com.example.catbreeds.data.entities.CatBreedImage
import com.example.catbreeds.data.repository.Repository
import com.example.catbreeds.utils.Resource

class CatBreedsViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    val catBreeds = repository.getBreeds()

    private var _id = MutableLiveData<String>()

    private val _catBreedImage = _id.switchMap { id ->
        repository.getImageByBreed(id)
    }

    var catBreedImage: LiveData<Resource<List<CatBreedImage>>> = _catBreedImage

    fun start(id: String) {
        _id.value = id
    }
}