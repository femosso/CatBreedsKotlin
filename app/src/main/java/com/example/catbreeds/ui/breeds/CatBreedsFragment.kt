package com.example.catbreeds.ui.breeds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catbreeds.R
import com.example.catbreeds.databinding.FragmentCatBreedsBinding
import com.example.catbreeds.utils.Resource
import com.example.catbreeds.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatBreedsFragment : Fragment(), CatBreedsAdapter.CatBreedItemListener {

    private var binding: FragmentCatBreedsBinding by autoCleared()
    private val viewModel: CatBreedsViewModel by viewModels()
    private lateinit var adapter: CatBreedsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCatBreedsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = CatBreedsAdapter(this)
        binding.catBreedsRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.catBreedsRecycler.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.catBreeds.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data?.data.isNullOrEmpty())
                        adapter.setCatBreeds(ArrayList(it.data?.data!!))
                    else
                        binding.noBreed.visibility = View.VISIBLE
                }
                Resource.Status.ERROR ->
                    binding.noBreed.visibility = View.VISIBLE

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.noBreed.visibility = View.GONE
                }
            }
        })
    }

    override fun onClickedCatBreed(breedId: String) {
        findNavController().navigate(
            R.id.action_catBreedsFragment_to_catBreedDetailsFragment,
            bundleOf("id" to breedId)
        )
    }
}