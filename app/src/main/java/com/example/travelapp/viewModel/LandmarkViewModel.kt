package com.example.travelapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.travelapp.repository.LandmarkRepository
import androidx.lifecycle.viewModelScope
import com.example.travelapp.model.Landmark
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LandmarkViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: LandmarkRepository = LandmarkRepository(application)


    fun getLandmarksForCity(cityId: Int): LiveData<List<Landmark>> {
        return repository.getLandmarksForCity(cityId)
    }

    fun insertLandmark(landmark: Landmark) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.insertLandmark(landmark)
        }
    }

    fun deleteLandmark(landmark: Landmark) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteLandmark(landmark)
        }
    }


}