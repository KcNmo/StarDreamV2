package com.starwish.app.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "users")
@Parcelize
data class User(
    @PrimaryKey
    val uid: String,
    val phoneNumber: String,
    val password: String? = null,
    val nickname: String,
    val avatar: String? = null,
    val personalSignature: String? = null,
    val personalIntro: String? = null,
    val birthday: String? = null,
    val constellation: String? = null,
    val interests: List<String> = emptyList(),
    val familySituation: String? = null,
    val dailyLife: String? = null,
    val educationWork: String? = null,
    val idealPartner: String? = null,
    val height: Int? = null,
    val gender: String? = null,
    val age: Int? = null,
    val isAvatarVerified: Boolean = false,
    val isEducationVerified: Boolean = false,
    val isIdentityVerified: Boolean = false,
    val photos: List<String> = emptyList(),
    val createdAt: Long = System.currentTimeMillis(),
    val lastActiveAt: Long = System.currentTimeMillis()
) : Parcelable