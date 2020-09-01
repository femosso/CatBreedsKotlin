package com.example.catbreeds.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.catbreeds.data.model.CatBreedImage
import com.example.catbreeds.data.repository.Repository
import com.example.catbreeds.utils.Resource

class CatBreedsViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    val catBreeds = repository.getBreeds()

    private val _selectedBreed = MutableLiveData<String>()

    private val _selectedBreedImage = _selectedBreed.switchMap { id ->
        repository.getImageByBreed(id)
    }

    val catBreedImage: LiveData<Resource<List<CatBreedImage>>> = _selectedBreedImage

    fun select(id: String) {
        _selectedBreed.value = id
    }

    fun fetchImage(breedId: String): LiveData<Resource<List<CatBreedImage>>> {
        return repository.getImageByBreed(breedId)
    }
}