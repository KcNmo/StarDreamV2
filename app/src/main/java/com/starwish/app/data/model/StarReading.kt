package com.starwish.app.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "star_readings")
@Parcelize
data class StarReading(
    @PrimaryKey
    val id: String,
    val userId: String,
    val date: String,
    val constellation: String,
    val reading: String,
    val luckyNumbers: List<Int> = emptyList(),
    val luckyColors: List<String> = emptyList(),
    val compatibility: List<String> = emptyList(),
    val advice: String? = null,
    val createdAt: Long = System.currentTimeMillis()
) : Parcelable

@Entity(tableName = "star_recommendations")
@Parcelize
data class StarRecommendation(
    @PrimaryKey
    val id: String,
    val userId: String,
    val recommendedUserId: String,
    val compatibility: Float,
    val reason: String,
    val starAvatar: String,
    val createdAt: Long = System.currentTimeMillis()
) : Parcelable