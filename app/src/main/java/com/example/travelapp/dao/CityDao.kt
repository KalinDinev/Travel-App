package com.example.travelapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.travelapp.model.City


@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCity(city: City)

    @Query("SELECT * FROM city_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<City>>

    @Query("SELECT COUNT(*) FROM landmark_table WHERE cityId = :cityId")
    suspend fun getLandmarkCountForCity(cityId: Long): Int

    @Delete
    suspend fun delete(city: City)
}