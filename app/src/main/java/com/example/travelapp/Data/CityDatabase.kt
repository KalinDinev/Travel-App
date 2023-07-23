package com.example.travelapp.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.travelapp.Model.City
import com.example.travelapp.Model.Landmark

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