package com.starwish.social.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.starwish.social.data.model.Message
import com.starwish.social.data.model.MessageType

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey
    val id: String,
    val senderId: String,
    val receiverId: String,
    val content: String,
    val type: String,
    val timestamp: Long,
    val isRead: Boolean,
    val isStarMessage: Boolean,
    val starLevel: Int
) {
    fun toMessage(): Message {
        return Message(
            id = id,
            senderId = senderId,
            receiverId = receiverId,
            content = content,
            type = MessageType.valueOf(type),
            timestamp = timestamp,
            isRead = isRead,
            isStarMessage = isStarMessage,
            starLevel = starLevel
        )
    }
    
    companion object {
        fun fromMessage(message: Message): MessageEntity {
            return MessageEntity(
                id = message.id,
                senderId = message.senderId,
                receiverId = message.receiverId,
                content = message.content,
                type = message.type.name,
                timestamp = message.timestamp,
                isRead = message.isRead,
                isStarMessage = message.isStarMessage,
                starLevel = message.starLevel
            )
        }
    }
}