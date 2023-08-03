package com.example.travelapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.travelapp.data.CityDatabase
import com.example.travelapp.repository.CityRepository
import com.example.travelapp.model.City
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CityViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<City>>
    private val repository: CityRepository

    init {
        val cityDao = CityDatabase.getDataBase(application).cityDao()
        repository = CityRepository(cityDao, application)
        readAllData = repository.readAllData
    }

    fun addCity(city: City) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCity(city)
        }
    }


    fun deleteCity(city: City) {
        viewModelScope.launch {
            repository.deleteCity(city)

        }
    }
}