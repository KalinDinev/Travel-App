package ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.travelapp.Data.CityDatabase
import Repository.CityRepository
import com.example.travelapp.Model.City
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