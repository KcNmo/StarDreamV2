package com.starwish.app.data.dao

import androidx.room.*
import com.starwish.app.data.model.Chat
import com.starwish.app.data.model.Message
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {
    @Query("SELECT * FROM chats WHERE userId1 = :userId OR userId2 = :userId ORDER BY lastMessageTime DESC")
    fun getChatsByUserId(userId: String): Flow<List<Chat>>

    @Query("SELECT * FROM chats WHERE id = :chatId")
    suspend fun getChatById(chatId: String): Chat?

    @Query("SELECT * FROM chats WHERE (userId1 = :userId1 AND userId2 = :userId2) OR (userId1 = :userId2 AND userId2 = :userId1)")
    suspend fun getChatBetweenUsers(userId1: String, userId2: String): Chat?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChat(chat: Chat)

    @Update
    suspend fun updateChat(chat: Chat)

    @Query("SELECT * FROM messages WHERE chatId = :chatId ORDER BY timestamp ASC")
    fun getMessagesByChatId(chatId: String): Flow<List<Message>>

    @Query("SELECT * FROM messages WHERE chatId = :chatId ORDER BY timestamp DESC LIMIT 1")
    suspend fun getLastMessage(chatId: String): Message?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: Message)

    @Update
    suspend fun updateMessage(message: Message)

    @Query("UPDATE messages SET isRead = 1 WHERE chatId = :chatId AND receiverId = :userId")
    suspend fun markMessagesAsRead(chatId: String, userId: String)

    @Query("SELECT COUNT(*) FROM messages WHERE chatId = :chatId AND receiverId = :userId AND isRead = 0")
    suspend fun getUnreadMessageCount(chatId: String, userId: String): Int
}