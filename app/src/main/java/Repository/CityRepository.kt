package Repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.travelapp.Data.CityDao
import com.example.travelapp.Model.City

class CityRepository(private val cityDao: CityDao, private val context: Application) {

    val readAllData: LiveData<List<City>> = cityDao.readAllData()

    suspend fun addCity(city: City) {
        cityDao.addCity(city)
    }


    suspend fun deleteCity(city: City) {
        val landmarkCount = cityDao.getLandmarkCountForCity(city.id.toLong())
        if (landmarkCount == 0) {
            cityDao.delete(city)
            Toast.makeText(
                context,
                "Deleted!",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                context,
                "This city has landmarks. Cannot be deleted!.",
                Toast.LENGTH_SHORT
            ).show()

        }
    }
}