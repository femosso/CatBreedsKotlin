package com.example.catbreeds.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.catbreeds.R
import com.example.catbreeds.data.entities.CatBreedImage
import com.example.catbreeds.databinding.FragmentCatBreedDetailsBinding
import com.example.catbreeds.utils.Resource
import com.example.catbreeds.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatBreedDetailsFragment : Fragment() {
    private var binding: FragmentCatBreedDetailsBinding by autoCleared()
    private val viewModel: CatBreedDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCatBreedDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("id")?.let { viewModel.start(it) }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.catBreedImage.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    bindCatBreed(it.data?.data!![0])
                    binding.progressBar.visibility = View.GONE
                    binding.linearLayout.visibility = View.VISIBLE
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.linearLayout.visibility = View.GONE
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