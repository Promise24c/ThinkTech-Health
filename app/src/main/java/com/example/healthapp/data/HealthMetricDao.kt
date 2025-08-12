package com.example.healthapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.healthapp.model.HealthMetric
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the HealthMetric entity.
 */
@Dao
interface HealthMetricDao {
    /**
     * Inserts a new health metric reading into the database.
     * @param healthMetric The health metric to insert.
     */
    @Insert
    suspend fun insert(healthMetric: HealthMetric)

    /**
     * Retrieves all health metrics for a specific user, ordered by date descending.
     * @param userId The ID of the user whose health metrics to retrieve.
     * @return A Flow that emits a list of health metrics.
     */
    @Query("SELECT * FROM health_metrics WHERE userId = :userId ORDER BY date DESC")
    fun getHealthMetricsForUser(userId: Int): Flow<List<HealthMetric>>
}
