package com.example.catbreeds.ui.breeds

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.catbreeds.data.model.CatBreed
import com.example.catbreeds.databinding.ViewCatBreedBinding

class CatBreedsAdapter(private val listener: CatBreedItemListener) :
    RecyclerView.Adapter<CatBreedsAdapter.CatBreedViewHolder>() {

    private val catBreeds = ArrayList<CatBreed>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatBreedViewHolder {
        val binding: ViewCatBreedBinding =
            ViewCatBreedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatBreedViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = catBreeds.size

    override fun onBindViewHolder(holder: CatBreedViewHolder, position: Int) =
        holder.bind(catBreeds[position])

    fun setCatBreeds(catBreeds: ArrayList<CatBreed>) {
        this.catBreeds.clear()
        this.catBreeds.addAll(catBreeds)
        notifyDataSetChanged()
    }

    interface CatBreedItemListener {
        fun onClickedCatBreed(breedId: String)
        fun onLoadCatBreedImage(breedId: String, imageView: ImageView)
    }

    class CatBreedViewHolder(
        private val binding: ViewCatBreedBinding,
        private val listener: CatBreedItemListener
    ) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        private lateinit var catBreed: CatBreed

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(item: CatBreed) {
            listener.onLoadCatBreedImage(item.id, binding.image)
            binding.name.text = item.name
            catBreed = item
        }

        override fun onClick(v: View?) {
            listener.onClickedCatBreed(catBreed.id)
        }
    }
}
