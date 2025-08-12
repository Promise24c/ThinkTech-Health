package com.example.healthapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.healthapp.model.Checkup
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the Checkup entity.
 */
@Dao
interface CheckupDao {
    /**
     * Inserts a new checkup into the database.
     * @param checkup The checkup to insert.
     */
    @Insert
    suspend fun insert(checkup: Checkup)

    /**
     * Retrieves all checkups for a specific user, ordered by date descending.
     * @param userId The ID of the user whose checkups to retrieve.
     * @return A Flow that emits a list of checkups.
     */
    @Query("SELECT * FROM checkups WHERE userId = :userId ORDER BY date DESC")
    fun getCheckupsForUser(userId: Int): Flow<List<Checkup>>
}
