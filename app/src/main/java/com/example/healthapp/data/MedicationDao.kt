package com.example.healthapp.data

import androidx.room.*
import com.example.healthapp.model.Medication
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the Medication entity.
 */
@Dao
interface MedicationDao {
    /**
     * Inserts a new medication into the database.
     * @param medication The medication to insert.
     * @return The rowId of the inserted medication.
     */
    @Insert
    suspend fun insert(medication: Medication): Long

    /**
     * Updates an existing medication in the database.
     * @param medication The medication to update.
     */
    @Update
    suspend fun update(medication: Medication)

    /**
     * Deletes a medication from the database.
     * @param medication The medication to delete.
     */
    @Delete
    suspend fun delete(medication: Medication)

    /**
     * Retrieves all medications for a specific user.
     * @param userId The ID of the user whose medications to retrieve.
     * @return A Flow that emits a list of medications.
     */
    @Query("SELECT * FROM medications WHERE userId = :userId")
    fun getMedicationsForUser(userId: Int): Flow<List<Medication>>

    /**
     * Retrieves all medications for a specific user that are low in stock.
     * @param userId The ID of the user whose low-stock medications to retrieve.
     * @return A Flow that emits a list of low-stock medications.
     */
    @Query("SELECT * FROM medications WHERE userId = :userId AND stock <= lowStockThreshold")
    fun getLowStockMedicationsForUser(userId: Int): Flow<List<Medication>>
}
