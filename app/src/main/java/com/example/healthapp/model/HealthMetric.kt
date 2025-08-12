package com.example.healthapp.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "health_metrics",
    foreignKeys = [ForeignKey(entity = User::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE)])
data class HealthMetric(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val date: Long,
    val bloodPressure: String,
    val bloodSugar: String,
    val heartRate: String,
    val notes: String
)
