package com.example.travelapp.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "landmark_table")
data class Landmark(
    @PrimaryKey(autoGenerate = true)
    val landmarkId: Int,
    val cityId: Int,
    val landmarkName: String,
    val landmarkDescription: String
)
