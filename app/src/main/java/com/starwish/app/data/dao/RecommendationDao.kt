package com.starwish.app.data.dao

import androidx.room.*
import com.starwish.app.data.model.Recommendation
import com.starwish.app.data.model.RecommendationType
import kotlinx.coroutines.flow.Flow

@Dao
interface RecommendationDao {
    @Query("SELECT * FROM recommendations WHERE userId = :userId AND type = :type ORDER BY createdAt DESC")
    fun getRecommendationsByType(userId: String, type: RecommendationType): Flow<List<Recommendation>>

    @Query("SELECT * FROM recommendations WHERE userId = :userId AND type = :type AND DATE(createdAt/1000, 'unixepoch') = DATE(:date/1000, 'unixepoch')")
    suspend fun getTodayRecommendations(userId: String, type: RecommendationType, date: Long): List<Recommendation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecommendation(recommendation: Recommendation)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecommendations(recommendations: List<Recommendation>)

    @Delete
    suspend fun deleteRecommendation(recommendation: Recommendation)

    @Query("DELETE FROM recommendations WHERE userId = :userId AND type = :type")
    suspend fun deleteRecommendationsByType(userId: String, type: RecommendationType)
}