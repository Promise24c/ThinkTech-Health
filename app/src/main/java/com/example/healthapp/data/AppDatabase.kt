package com.example.healthapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.healthapp.model.Checkup
import com.example.healthapp.model.HealthMetric
import com.example.healthapp.model.Medication
import com.example.healthapp.model.User

@Database(entities = [User::class, Medication::class, HealthMetric::class, Checkup::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun medicationDao(): MedicationDao
    abstract fun healthMetricDao(): HealthMetricDao
    abstract fun checkupDao(): CheckupDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "health_app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
