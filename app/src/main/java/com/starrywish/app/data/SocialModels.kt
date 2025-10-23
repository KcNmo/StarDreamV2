package com.starrywish.app.data

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
enum class VerificationStatus { UNVERIFIED, PENDING, VERIFIED }

@Serializable
data class SocialCard(
    val about: String = "",
    val birthday: LocalDate? = null,
    val zodiac: String? = null,
    val interests: List<String> = emptyList(),
    val family: String = "",
    val dailyLife: String = "",
    val educationCareer: String = "",
    val ideal: String = "",
    val photos: List<String> = emptyList(),
    val avatarVerified: VerificationStatus = VerificationStatus.UNVERIFIED,
    val educationVerified: VerificationStatus = VerificationStatus.UNVERIFIED,
    val identityVerified: VerificationStatus = VerificationStatus.UNVERIFIED,
)

@Serializable
data class Message(
    val id: String,
    val conversationId: String,
    val senderUid: String,
    val content: String,
    val sentAt: LocalDateTime,
)

@Serializable
enum class ConversationKind { FRIEND, ASTRO_STRANGER }

@Serializable
data class Conversation(
    val id: String,
    val userA: String,
    val userB: String,
    val kind: ConversationKind,
    val lastInteractionDate: LocalDate,
    val disguiseAvatarSeed: String? = null,
    val friendStreakDays: Int = 0,
)

@Serializable
enum class Visibility { CIRCLE, PUBLIC }

@Serializable
data class Post(
    val id: String,
    val authorUid: String,
    val title: String,
    val text: String,
    val imageUrls: List<String> = emptyList(),
    val visibility: Visibility = Visibility.PUBLIC,
    val groups: List<String> = emptyList(),
    val createdAt: LocalDateTime,
)
