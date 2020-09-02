package com.example.catbreeds.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.catbreeds.data.model.CatBreed
import com.example.catbreeds.data.model.CatBreedImage
import com.example.catbreeds.data.repository.Repository
import com.example.catbreeds.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SingleNetworkCallViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var catBreedsObserver: Observer<Resource<List<CatBreedImage>>>

    private lateinit var viewModel: CatBreedsViewModel

    @Before
    fun setUp() {
        viewModel = CatBreedsViewModel(repository)
        viewModel.catBreedImage.observeForever(catBreedsObserver)
        createDummyContent()
    }

    private fun createDummyContent() {
        for (i in 0..9) {
            CAT_BREED_LIST.add(createDummyCatBreed(i))
            CAT_BREED_IMAGE_LIST.add(createDummyCatBreedImage(i))
        }
    }

    private fun createDummyCatBreed(i: Int): CatBreed {
        return CatBreed("BreedId-$i", "Name: $i", "Description: $i")
    }

    private fun createDummyCatBreedImage(i: Int): CatBreedImage {
        return CatBreedImage(url = "url-$i")
    }

    companion object {
        private lateinit var CAT_BREED_LIST: ArrayList<CatBreed>
        private lateinit var CAT_BREED_IMAGE_LIST: ArrayList<CatBreedImage>
    }

}