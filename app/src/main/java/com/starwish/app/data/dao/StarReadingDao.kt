package com.starwish.app.data.dao

import androidx.room.*
import com.starwish.app.data.model.StarReading
import com.starwish.app.data.model.StarRecommendation
import kotlinx.coroutines.flow.Flow

@Dao
interface StarReadingDao {
    @Query("SELECT * FROM star_readings WHERE userId = :userId ORDER BY createdAt DESC")
    fun getStarReadingsByUserId(userId: String): Flow<List<StarReading>>

    @Query("SELECT * FROM star_readings WHERE userId = :userId AND date = :date")
    suspend fun getStarReadingByDate(userId: String, date: String): StarReading?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStarReading(reading: StarReading)

    @Query("SELECT * FROM star_recommendations WHERE userId = :userId ORDER BY createdAt DESC")
    fun getStarRecommendationsByUserId(userId: String): Flow<List<StarRecommendation>>

    @Query("SELECT * FROM star_recommendations WHERE userId = :userId AND DATE(createdAt/1000, 'unixepoch') = DATE(:date/1000, 'unixepoch')")
    suspend fun getTodayStarRecommendations(userId: String, date: Long): List<StarRecommendation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStarRecommendation(recommendation: StarRecommendation)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStarRecommendations(recommendations: List<StarRecommendation>)
}