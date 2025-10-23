package com.starwish.social.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.starwish.social.data.model.User

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val uid: String,
    val phoneNumber: String,
    val nickname: String,
    val avatar: String,
    val personalSignature: String,
    val socialCardJson: String,
    val photoAlbumJson: String,
    val isVerified: Boolean,
    val isOnline: Boolean,
    val lastActiveTime: Long,
    val createdAt: Long,
    val updatedAt: Long
) {
    fun toUser(): User {
        return User(
            uid = uid,
            phoneNumber = phoneNumber,
            nickname = nickname,
            avatar = avatar,
            personalSignature = personalSignature,
            socialCard = SocialCard(), // TODO: 从JSON解析
            photoAlbum = emptyList(), // TODO: 从JSON解析
            isVerified = isVerified,
            isOnline = isOnline,
            lastActiveTime = lastActiveTime,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }
    
    companion object {
        fun fromUser(user: User): UserEntity {
            return UserEntity(
                uid = user.uid,
                phoneNumber = user.phoneNumber,
                nickname = user.nickname,
                avatar = user.avatar,
                personalSignature = user.personalSignature,
                socialCardJson = "", // TODO: 转换为JSON
                photoAlbumJson = "", // TODO: 转换为JSON
                isVerified = user.isVerified,
                isOnline = user.isOnline,
                lastActiveTime = user.lastActiveTime,
                createdAt = user.createdAt,
                updatedAt = user.updatedAt
            )
        }
    }
}