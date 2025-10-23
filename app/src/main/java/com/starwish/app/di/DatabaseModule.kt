package com.starwish.app.di

import android.content.Context
import androidx.room.Room
import com.starwish.app.data.database.StarWishDatabase
import com.starwish.app.data.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): StarWishDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            StarWishDatabase::class.java,
            "starwish_database"
        ).build()
    }
    
    @Provides
    fun provideUserDao(database: StarWishDatabase): UserDao = database.userDao()
    
    @Provides
    fun provideRecommendationDao(database: StarWishDatabase): RecommendationDao = database.recommendationDao()
    
    @Provides
    fun provideMatchDao(database: StarWishDatabase): MatchDao = database.matchDao()
    
    @Provides
    fun provideMessageDao(database: StarWishDatabase): MessageDao = database.messageDao()
    
    @Provides
    fun providePostDao(database: StarWishDatabase): PostDao = database.postDao()
    
    @Provides
    fun provideStarReadingDao(database: StarWishDatabase): StarReadingDao = database.starReadingDao()
}