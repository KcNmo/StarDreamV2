package com.starwish.social.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val uid: String = "",
    val phoneNumber: String = "",
    val nickname: String = "",
    val avatar: String = "",
    val personalSignature: String = "",
    val socialCard: SocialCard = SocialCard(),
    val photoAlbum: List<String> = emptyList(),
    val isVerified: Boolean = false,
    val isOnline: Boolean = false,
    val lastActiveTime: Long = 0L,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) : Parcelable

@Parcelize
data class SocialCard(
    val personalIntro: String = "",
    val birthday: String = "",
    val constellation: String = "",
    val interests: List<String> = emptyList(),
    val familySituation: String = "",
    val dailyLife: String = "",
    val education: String = "",
    val work: String = "",
    val idealPartner: String = "",
    val height: Int = 0,
    val location: String = "",
    val gender: String = "",
    val age: Int = 0
) : Parcelable

@Parcelize
data class Verification(
    val avatarVerified: Boolean = false,
    val educationVerified: Boolean = false,
    val identityVerified: Boolean = false,
    val verificationDocuments: List<String> = emptyList()
) : Parcelable

@Parcelize
data class StarProfile(
    val starSign: String = "",
    val starChart: String = "",
    val compatibility: List<String> = emptyList(),
    val dailyHoroscope: String = "",
    val starPower: Int = 0
) : Parcelable