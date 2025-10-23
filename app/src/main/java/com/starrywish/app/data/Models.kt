package com.starrywish.app.data

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val uid: String,
    val phone: String,
    val nickname: String,
    val avatarUrl: String? = null,
    val bio: String = "",
    val birthday: LocalDate? = null,
    val zodiac: String? = null,
)

@Serializable
data class Session(
    val uid: String,
    val token: String,
)
