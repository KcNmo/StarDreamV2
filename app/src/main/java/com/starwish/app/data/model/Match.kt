package com.starwish.app.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "matches")
@Parcelize
data class Match(
    @PrimaryKey
    val id: String,
    val userId1: String,
    val userId2: String,
    val isMutual: Boolean = false,
    val isStarStranger: Boolean = false,
    val starConnectionDays: Int = 0,
    val lastChatAt: Long? = null,
    val createdAt: Long = System.currentTimeMillis()
) : Parcelable

@Entity(tableName = "user_actions")
@Parcelize
data class UserAction(
    @PrimaryKey
    val id: String,
    val userId: String,
    val targetUserId: String,
    val action: ActionType,
    val createdAt: Long = System.currentTimeMillis()
) : Parcelable

@Parcelize
enum class ActionType : Parcelable {
    LIKE,
    DISLIKE,
    SUPER_LIKE
}