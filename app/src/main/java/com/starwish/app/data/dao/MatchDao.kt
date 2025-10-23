package com.starwish.app.data.dao

import androidx.room.*
import com.starwish.app.data.model.Match
import com.starwish.app.data.model.UserAction
import com.starwish.app.data.model.ActionType
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchDao {
    @Query("SELECT * FROM matches WHERE userId1 = :userId OR userId2 = :userId")
    fun getMatchesByUserId(userId: String): Flow<List<Match>>

    @Query("SELECT * FROM matches WHERE (userId1 = :userId1 AND userId2 = :userId2) OR (userId1 = :userId2 AND userId2 = :userId1)")
    suspend fun getMatchBetweenUsers(userId1: String, userId2: String): Match?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatch(match: Match)

    @Update
    suspend fun updateMatch(match: Match)

    @Delete
    suspend fun deleteMatch(match: Match)

    @Query("SELECT * FROM user_actions WHERE userId = :userId AND targetUserId = :targetUserId")
    suspend fun getUserAction(userId: String, targetUserId: String): UserAction?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserAction(action: UserAction)

    @Query("SELECT * FROM user_actions WHERE userId = :userId ORDER BY createdAt DESC")
    fun getUserActions(userId: String): Flow<List<UserAction>>

    @Query("SELECT * FROM user_actions WHERE userId = :userId AND action = :action ORDER BY createdAt DESC")
    fun getUserActionsByType(userId: String, action: ActionType): Flow<List<UserAction>>
}