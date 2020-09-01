package com.example.catbreeds.ui.breeds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catbreeds.R
import com.example.catbreeds.databinding.FragmentCatBreedsBinding
import com.example.catbreeds.ui.viewmodel.CatBreedsViewModel
import com.example.catbreeds.utils.Resource
import com.example.catbreeds.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatBreedsFragment : Fragment(), CatBreedsAdapter.CatBreedItemListener {

    private var binding: FragmentCatBreedsBinding by autoCleared()
    private var viewModel: CatBreedsViewModel? = null
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
        viewModel = ViewModelProvider(requireActivity()).get(CatBreedsViewModel::class.java)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = CatBreedsAdapter(this)
        binding.catBreedsRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.catBreedsRecycler.adapter = adapter
    }

    private fun setupObservers() {
        viewModel?.catBreeds?.observe(viewLifecycleOwner, Observer { catBreeds ->
            when (catBreeds.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (catBreeds.data == null)
                        binding.noBreed.visibility = View.VISIBLE
                    else
                        adapter.setCatBreeds(ArrayList(catBreeds.data))
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
        viewModel?.start(breedId)
        findNavController().navigate(
            R.id.action_catBreedsFragment_to_catBreedDetailsFragment
        )
    }
}