package com.example.catbreeds.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.catbreeds.R
import com.example.catbreeds.data.model.CatBreedImage
import com.example.catbreeds.databinding.FragmentCatBreedDetailsBinding
import com.example.catbreeds.ui.viewmodel.CatBreedsViewModel
import com.example.catbreeds.utils.Resource
import com.example.catbreeds.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatBreedDetailsFragment : Fragment() {

    private var binding: FragmentCatBreedDetailsBinding by autoCleared()
    private var viewModel: CatBreedsViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCatBreedDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(CatBreedsViewModel::class.java)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel?.catBreedImage?.observe(viewLifecycleOwner, Observer { cattBreed ->
            when (cattBreed.status) {
                Resource.Status.SUCCESS -> {
                    cattBreed.data?.get(0)?.let { bindCatBreed(it) }
                    binding.progressBar.visibility = View.GONE
                    binding.linearLayout.visibility = View.VISIBLE
                    binding.noBreedDetails.visibility = View.GONE
                }

                Resource.Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.linearLayout.visibility = View.GONE
                    binding.noBreedDetails.visibility = View.VISIBLE
                }

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.linearLayout.visibility = View.GONE
                    binding.noBreedDetails.visibility = View.GONE
                }
            }
        })
    }

    private fun bindCatBreed(catBreedImage: CatBreedImage) {
        val catBreed = catBreedImage.breeds[0]
        binding.breedName.text = catBreed.name
        binding.description.text = catBreed.description
        binding.moreInformation.text = catBreed.wikipediaUrl
        binding.energyLevel.rating = catBreed.energyLevel.toFloat()
        binding.dogFriendly.rating = catBreed.dogFriendly.toFloat()
        binding.healthIssues.rating = catBreed.healthIssues.toFloat()

        Glide.with(binding.root)
            .load(catBreedImage.url)
            .apply(RequestOptions().placeholder(R.drawable.placeholder))
            .into(binding.breedImage)

        Glide.with(binding.root)
            .load(
                String.format(
                    "https://www.countryflags.io/%s/shiny/64.png",
                    catBreed.countryCode
                )
            )
            .apply(RequestOptions().placeholder(R.drawable.placeholder))
            .into(binding.countryFlag)
    }
}