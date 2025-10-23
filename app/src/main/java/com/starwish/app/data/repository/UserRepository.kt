package com.starwish.app.data.repository

import com.starwish.app.data.dao.UserDao
import com.starwish.app.data.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    suspend fun getUserById(uid: String): User? = userDao.getUserById(uid)
    
    suspend fun getUserByPhone(phoneNumber: String): User? = userDao.getUserByPhone(phoneNumber)
    
    suspend fun getUsersByIds(uids: List<String>): List<User> = userDao.getUsersByIds(uids)
    
    suspend fun insertUser(user: User) = userDao.insertUser(user)
    
    suspend fun updateUser(user: User) = userDao.updateUser(user)
    
    suspend fun deleteUser(user: User) = userDao.deleteUser(user)
    
    fun getAllUsersExceptCurrent(currentUserId: String): Flow<List<User>> = 
        userDao.getAllUsersExceptCurrent(currentUserId)
    
    suspend fun searchUsers(currentUserId: String, query: String): List<User> = 
        userDao.searchUsers(currentUserId, query)
    
    fun generateUID(): String {
        return (10000000..99999999).random().toString()
    }
    
    fun getConstellation(birthday: String): String {
        val parts = birthday.split("-")
        if (parts.size != 3) return ""
        
        val month = parts[1].toIntOrNull() ?: return ""
        val day = parts[2].toIntOrNull() ?: return ""
        
        return when {
            (month == 3 && day >= 21) || (month == 4 && day <= 19) -> "白羊座"
            (month == 4 && day >= 20) || (month == 5 && day <= 20) -> "金牛座"
            (month == 5 && day >= 21) || (month == 6 && day <= 21) -> "双子座"
            (month == 6 && day >= 22) || (month == 7 && day <= 22) -> "巨蟹座"
            (month == 7 && day >= 23) || (month == 8 && day <= 22) -> "狮子座"
            (month == 8 && day >= 23) || (month == 9 && day <= 22) -> "处女座"
            (month == 9 && day >= 23) || (month == 10 && day <= 23) -> "天秤座"
            (month == 10 && day >= 24) || (month == 11 && day <= 22) -> "天蝎座"
            (month == 11 && day >= 23) || (month == 12 && day <= 21) -> "射手座"
            (month == 12 && day >= 22) || (month == 1 && day <= 19) -> "摩羯座"
            (month == 1 && day >= 20) || (month == 2 && day <= 18) -> "水瓶座"
            (month == 2 && day >= 19) || (month == 3 && day <= 20) -> "双鱼座"
            else -> ""
        }
    }
}