package com.starwish.social.data.local.dao

import androidx.room.*
import com.starwish.social.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE uid = :uid")
    suspend fun getUserById(uid: String): UserEntity?
    
    @Query("SELECT * FROM users WHERE phoneNumber = :phoneNumber")
    suspend fun getUserByPhone(phoneNumber: String): UserEntity?
    
    @Query("SELECT * FROM users WHERE uid IN (:uids)")
    suspend fun getUsersByIds(uids: List<String>): List<UserEntity>
    
    @Query("SELECT * FROM users WHERE isOnline = 1")
    fun getOnlineUsers(): Flow<List<UserEntity>>
    
    @Query("SELECT * FROM users WHERE nickname LIKE '%' || :query || '%' OR personalSignature LIKE '%' || :query || '%'")
    suspend fun searchUsers(query: String): List<UserEntity>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)
    
    @Update
    suspend fun updateUser(user: UserEntity)
    
    @Delete
    suspend fun deleteUser(user: UserEntity)
    
    @Query("DELETE FROM users WHERE uid = :uid")
    suspend fun deleteUserById(uid: String)
    
    @Query("UPDATE users SET isOnline = :isOnline, lastActiveTime = :lastActiveTime WHERE uid = :uid")
    suspend fun updateUserOnlineStatus(uid: String, isOnline: Boolean, lastActiveTime: Long)
}