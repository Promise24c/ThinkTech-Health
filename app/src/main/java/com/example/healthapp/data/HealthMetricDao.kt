package com.example.healthapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.healthapp.model.HealthMetric
import kotlinx.coroutines.flow.Flow

@Dao
interface HealthMetricDao {
    @Insert
    suspend fun insert(healthMetric: HealthMetric)

    @Query("SELECT * FROM health_metrics WHERE userId = :userId ORDER BY date DESC")
    fun getHealthMetricsForUser(userId: Int): Flow<List<HealthMetric>>
}
