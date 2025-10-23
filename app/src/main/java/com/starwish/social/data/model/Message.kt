package com.starwish.social.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Message(
    val id: String = "",
    val senderId: String = "",
    val receiverId: String = "",
    val content: String = "",
    val type: MessageType = MessageType.TEXT,
    val timestamp: Long = System.currentTimeMillis(),
    val isRead: Boolean = false,
    val isStarMessage: Boolean = false,
    val starLevel: Int = 0
) : Parcelable

enum class MessageType {
    TEXT, IMAGE, VOICE, VIDEO, STAR_GIFT, SYSTEM
}

@Parcelize
data class Conversation(
    val id: String = "",
    val participants: List<String> = emptyList(),
    val lastMessage: Message? = null,
    val unreadCount: Int = 0,
    val isStarConversation: Boolean = false,
    val starLevel: Int = 0,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) : Parcelable