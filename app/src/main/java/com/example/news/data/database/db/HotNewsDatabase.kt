package com.example.news.data.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.news.data.database.entity.HotNewsEntity
import com.example.news.data.database.remote.HotNewsDao


@Database(
    entities = [HotNewsEntity::class],
    version = 1,
    exportSchema = true,
)
abstract class HotNewsDatabase : RoomDatabase() {
    abstract fun newsDao(): HotNewsDao
}