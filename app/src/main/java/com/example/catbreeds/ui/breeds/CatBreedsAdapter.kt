package com.example.catbreeds.ui.breeds

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.catbreeds.R
import com.example.catbreeds.data.entities.CatBreed
import com.example.catbreeds.databinding.ViewCatBreedBinding

class CatBreedsAdapter(private val listener: CatBreedItemListener) :
    RecyclerView.Adapter<CatBreedViewHolder>() {

    interface CatBreedItemListener {
        fun onClickedCatBreed(breedId: String)
    }

    private val catBreeds = ArrayList<CatBreed>()

    fun setCatBreeds(catBreeds: ArrayList<CatBreed>) {
        this.catBreeds.clear()
        this.catBreeds.addAll(catBreeds)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatBreedViewHolder {
        val binding: ViewCatBreedBinding =
            ViewCatBreedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatBreedViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = catBreeds.size

    override fun onBindViewHolder(holder: CatBreedViewHolder, position: Int) =
        holder.bind(catBreeds[position])
}

class CatBreedViewHolder(
    private val binding: ViewCatBreedBinding,
    private val listener: CatBreedsAdapter.CatBreedItemListener
) : RecyclerView.ViewHolder(binding.root),
    View.OnClickListener {

    private lateinit var catBreed: CatBreed

    init {
        binding.root.setOnClickListener(this)
    }

    fun bind(item: CatBreed) {
        this.catBreed = item
        binding.name.text = item.name
        binding.origin.text = item.origin

        Glide.with(binding.root)
            .load(
                String.format(
                    "https://www.countryflags.io/%s/shiny/64.png",
                    catBreed.countryCode
                )
            )
            .into(binding.countryFlag)
    }

    override fun onClick(v: View?) {
        listener.onClickedCatBreed(catBreed.id)
    }
}
