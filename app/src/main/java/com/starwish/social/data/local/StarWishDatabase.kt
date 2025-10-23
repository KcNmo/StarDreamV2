package com.starwish.social.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.starwish.social.data.local.dao.MessageDao
import com.starwish.social.data.local.dao.UserDao
import com.starwish.social.data.local.entity.MessageEntity
import com.starwish.social.data.local.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        MessageEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class StarWishDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun messageDao(): MessageDao
    
    companion object {
        @Volatile
        private var INSTANCE: StarWishDatabase? = null
        
        fun getDatabase(context: Context): StarWishDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StarWishDatabase::class.java,
                    "starwish_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}