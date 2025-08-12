package com.example.healthapp.data

import androidx.room.*
import com.example.healthapp.model.Medication
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicationDao {
    @Insert
    suspend fun insert(medication: Medication)

    @Update
    suspend fun update(medication: Medication)

    @Delete
    suspend fun delete(medication: Medication)

    @Query("SELECT * FROM medications WHERE userId = :userId")
    fun getMedicationsForUser(userId: Int): Flow<List<Medication>>

    @Query("SELECT * FROM medications WHERE userId = :userId AND stock <= lowStockThreshold")
    fun getLowStockMedicationsForUser(userId: Int): Flow<List<Medication>>
}
