package com.starwish.app.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "recommendations")
@Parcelize
data class Recommendation(
    @PrimaryKey
    val id: String,
    val userId: String,
    val recommendedUserId: String,
    val type: RecommendationType,
    val score: Float,
    val reason: String? = null,
    val createdAt: Long = System.currentTimeMillis()
) : Parcelable

@Parcelize
enum class RecommendationType : Parcelable {
    DAILY_RECOMMENDATION,
    STAR_RECOMMENDATION
}