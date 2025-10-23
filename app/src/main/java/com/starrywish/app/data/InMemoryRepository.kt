package com.starrywish.app.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalDate
import kotlin.random.Random

class InMemoryRepository {
    private val userProfiles = MutableStateFlow<List<UserProfile>>(emptyList())

    init {
        // seed sample users
        val sample = (1..50).map {
            val uid = (10000000 + it).toString()
            UserProfile(
                uid = uid,
                phone = "1880000%04d".format(it),
                nickname = "星友$it",
                avatarUrl = null,
                bio = "热爱学习与分享",
                birthday = LocalDate(2000, (it % 12) + 1, (it % 28) + 1),
                zodiac = listOf("白羊","金牛","双子","巨蟹","狮子","处女","天秤","天蝎","射手","摩羯","水瓶","双鱼")[it % 12]
            )
        }
        userProfiles.value = sample
    }

    fun observeProfiles(): StateFlow<List<UserProfile>> = userProfiles

    suspend fun register(phone: String, passwordOrCode: String, nickname: String): UserProfile {
        delay(400)
        val uid = generateUid()
        val profile = UserProfile(uid = uid, phone = phone, nickname = nickname)
        userProfiles.update { it + profile }
        return profile
    }

    suspend fun loginByUidOrPhone(idOrPhone: String, secret: String): Session? {
        delay(300)
        val target = userProfiles.value.firstOrNull { it.uid == idOrPhone || it.phone == idOrPhone }
        return target?.let { Session(uid = it.uid, token = Random.nextBytes(8).joinToString("") { b -> "%02x".format(b) }) }
    }

    private fun generateUid(): String = (10000000..99999999).random().toString()
}
