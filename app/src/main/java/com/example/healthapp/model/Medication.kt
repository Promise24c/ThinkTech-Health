package com.example.healthapp.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "medications",
    foreignKeys = [ForeignKey(entity = User::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE)])
data class Medication(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val name: String,
    val dosage: String,
    val frequency: String,
    val time: String, // e.g., "08:00,12:00,20:00"
    var stock: Int,
    val lowStockThreshold: Int
)
