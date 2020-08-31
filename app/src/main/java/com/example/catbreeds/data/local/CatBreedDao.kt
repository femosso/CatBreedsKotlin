package com.example.catbreeds.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.catbreeds.data.entities.CatBreed
import com.example.catbreeds.data.entities.CatBreedImage

@Dao
interface CatBreedDao {

    @Query("SELECT * FROM breeds")
    fun getBreeds(): LiveData<List<CatBreed>>

    @Query("SELECT * FROM breeds_image WHERE url = :breedId")
    fun getImageByBreed(breedId: String): LiveData<List<CatBreedImage>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBreeds(breeds: List<CatBreed>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(breedImage: CatBreedImage)

}