package com.example.travelapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.travelapp.dao.CityDao
import com.example.travelapp.dao.LandmarkDao
import com.example.travelapp.model.City
import com.example.travelapp.model.Landmark

@Database(entities = [City::class, Landmark::class], version = 1, exportSchema = false)
abstract class CityDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao
    abstract fun landmarkDao(): LandmarkDao

    companion object {
        @Volatile
        private var INSTANCE: CityDatabase? = null


        fun getDataBase(context: Context): CityDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CityDatabase::class.java,
                    "city_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}