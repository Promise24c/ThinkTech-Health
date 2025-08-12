package com.example.healthapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.healthapp.model.Checkup
import com.example.healthapp.model.HealthMetric
import com.example.healthapp.model.Medication
import com.example.healthapp.model.User

/**
 * The Room database for this app.
 *
 * This database holds the tables for User, Medication, HealthMetric, and Checkup.
 */
@Database(entities = [User::class, Medication::class, HealthMetric::class, Checkup::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    /**
     * @return The Data Access Object for the User entity.
     */
    abstract fun userDao(): UserDao
    /**
     * @return The Data Access Object for the Medication entity.
     */
    abstract fun medicationDao(): MedicationDao
    /**
     * @return The Data Access Object for the HealthMetric entity.
     */
    abstract fun healthMetricDao(): HealthMetricDao
    /**
     * @return The Data Access Object for the Checkup entity.
     */
    abstract fun checkupDao(): CheckupDao

    companion object {
        /**
         * The singleton instance of the AppDatabase.
         *
         * @Volatile ensures that writes to this field are immediately made visible to other threads.
         */
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Returns the singleton instance of the AppDatabase.
         *
         * If the instance is not null, returns it. Otherwise, creates the database in a
         * synchronized block to ensure only one instance is created.
         *
         * @param context The application context.
         * @return The singleton instance of the AppDatabase.
         */
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
