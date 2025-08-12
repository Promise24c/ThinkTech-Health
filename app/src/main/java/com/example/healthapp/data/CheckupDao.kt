package com.example.healthapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.healthapp.model.Checkup
import kotlinx.coroutines.flow.Flow

@Dao
interface CheckupDao {
    @Insert
    suspend fun insert(checkup: Checkup)

    @Query("SELECT * FROM checkups WHERE userId = :userId ORDER BY date DESC")
    fun getCheckupsForUser(userId: Int): Flow<List<Checkup>>
}
