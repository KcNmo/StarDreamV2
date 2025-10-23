package com.starwish.social.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Match(
    val id: String = "",
    val userId1: String = "",
    val userId2: String = "",
    val matchType: MatchType = MatchType.RECOMMENDATION,
    val compatibility: Int = 0,
    val isMatched: Boolean = false,
    val isStarMatch: Boolean = false,
    val starChart: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val matchedAt: Long? = null
) : Parcelable

enum class MatchType {
    RECOMMENDATION, STAR_CHART, MUTUAL_FRIEND, LOCATION
}

@Parcelize
data class Recommendation(
    val userId: String = "",
    val recommendedUsers: List<User> = emptyList(),
    val dailyCount: Int = 0,
    val maxDailyCount: Int = 21,
    val lastUpdated: Long = System.currentTimeMillis()
) : Parcelable

@Parcelize
data class StarChart(
    val userId: String = "",
    val chartData: String = "",
    val dailyHoroscope: String = "",
    val compatibleSigns: List<String> = emptyList(),
    val dailyCount: Int = 0,
    val maxDailyCount: Int = 5,
    val lastUpdated: Long = System.currentTimeMillis()
) : Parcelable

@Parcelize
data class StarConnection(
    val id: String = "",
    val userId1: String = "",
    val userId2: String = "",
    val starLevel: Int = 0,
    val consecutiveDays: Int = 0,
    val totalStars: Int = 0,
    val lastInteraction: Long = System.currentTimeMillis(),
    val isActive: Boolean = true
) : Parcelable