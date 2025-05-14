package com.example.news.di

import android.app.Application
import androidx.room.Room
import com.example.news.data.database.dao.NewsDao
import com.example.news.data.database.db.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application,
    ): NewsDatabase {
        return Room
            .databaseBuilder(application, NewsDatabase::class.java, "News.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(appDatabase: NewsDatabase): NewsDao {
        return appDatabase.newsDao()
    }
}