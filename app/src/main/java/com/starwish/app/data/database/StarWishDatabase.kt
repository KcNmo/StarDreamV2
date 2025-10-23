package com.starwish.app.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.starwish.app.data.dao.*
import com.starwish.app.data.model.*

@Database(
    entities = [
        User::class,
        Recommendation::class,
        Match::class,
        UserAction::class,
        Chat::class,
        Message::class,
        Post::class,
        PostLike::class,
        PostComment::class,
        StarReading::class,
        StarRecommendation::class
    ],
    version = 1,
    exportSchema = false
)
abstract class StarWishDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun recommendationDao(): RecommendationDao
    abstract fun matchDao(): MatchDao
    abstract fun messageDao(): MessageDao
    abstract fun postDao(): PostDao
    abstract fun starReadingDao(): StarReadingDao

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