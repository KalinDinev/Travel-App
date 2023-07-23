package Repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.travelapp.Data.CityDatabase
import com.example.travelapp.Data.LandmarkDao
import com.example.travelapp.Model.Landmark

class LandmarkRepository(application: Application) {


    private val landmarkDao: LandmarkDao
    private val allLandmarks: LiveData<List<Landmark>>

    init {
        val database: CityDatabase = CityDatabase.getDataBase(application)
        landmarkDao = database.landmarkDao()
        allLandmarks = landmarkDao.readAllData()
    }

    fun getLandmarksForCity(cityId: Int): LiveData<List<Landmark>> {
        return landmarkDao.getLandmarksForCity(cityId)
    }

    suspend fun insertLandmark(landmark: Landmark) {
        landmarkDao.insert(landmark)
    }

    suspend fun deleteLandmark(landmark: Landmark) {
        landmarkDao.delete(landmark)
    }
}