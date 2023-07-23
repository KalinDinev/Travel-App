package com.example.travelapp.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.travelapp.Model.Landmark


@Dao
interface LandmarkDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLandmark(landmark: Landmark)

    @Query("SELECT * FROM  landmark_table ORDER BY landmarkId ASC")
    fun readAllData(): LiveData<List<Landmark>>

    @Query("SELECT * FROM landmark_table WHERE cityId = :cityId")
    fun getLandmarksForCity(cityId: Int): LiveData<List<Landmark>>


    @Insert
    suspend fun insert(landmark: Landmark)

    @Delete
    suspend fun delete(landmark: Landmark)
}