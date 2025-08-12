package com.example.healthapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.healthapp.model.User

/**
 * Data Access Object for the User entity.
 */
@Dao
interface UserDao {
    /**
     * Inserts a new user into the database.
     * @param user The user to insert.
     */
    @Insert
    suspend fun insert(user: User)

    /**
     * Retrieves a user from the database by their username.
     * @param username The username of the user to retrieve.
     * @return The User object if found, otherwise null.
     */
    @Query("SELECT * FROM users WHERE username = :username")
    suspend fun getUserByUsername(username: String): User?
}
