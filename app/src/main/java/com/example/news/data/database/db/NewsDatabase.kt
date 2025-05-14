package com.example.news.data.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.news.data.database.dao.NewsDao
import com.example.news.data.database.entity.NewsEntity


@Database(
    entities = [NewsEntity::class],
    version = 1,
    exportSchema = true,
)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}