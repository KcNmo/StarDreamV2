package com.starwish.app.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "messages")
@Parcelize
data class Message(
    @PrimaryKey
    val id: String,
    val chatId: String,
    val senderId: String,
    val receiverId: String,
    val content: String,
    val type: MessageType,
    val timestamp: Long = System.currentTimeMillis(),
    val isRead: Boolean = false,
    val isStarStranger: Boolean = false
) : Parcelable

@Parcelize
enum class MessageType : Parcelable {
    TEXT,
    IMAGE,
    EMOJI,
    SYSTEM
}

@Entity(tableName = "chats")
@Parcelize
data class Chat(
    @PrimaryKey
    val id: String,
    val userId1: String,
    val userId2: String,
    val lastMessage: String? = null,
    val lastMessageTime: Long? = null,
    val isStarStranger: Boolean = false,
    val starConnectionDays: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
) : Parcelable