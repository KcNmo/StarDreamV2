package com.starwish.app.data.dao

import androidx.room.*
import com.starwish.app.data.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE uid = :uid")
    suspend fun getUserById(uid: String): User?

    @Query("SELECT * FROM users WHERE phoneNumber = :phoneNumber")
    suspend fun getUserByPhone(phoneNumber: String): User?

    @Query("SELECT * FROM users WHERE uid IN (:uids)")
    suspend fun getUsersByIds(uids: List<String>): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM users WHERE uid != :currentUserId ORDER BY lastActiveAt DESC")
    fun getAllUsersExceptCurrent(currentUserId: String): Flow<List<User>>

    @Query("SELECT * FROM users WHERE uid != :currentUserId AND (nickname LIKE '%' || :query || '%' OR personalSignature LIKE '%' || :query || '%')")
    suspend fun searchUsers(currentUserId: String, query: String): List<User>
}