package com.example.catbreeds.ui.breeds

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.catbreeds.data.repository.Repository

class CatBreedsViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    val catBreeds = repository.getBreeds()

}