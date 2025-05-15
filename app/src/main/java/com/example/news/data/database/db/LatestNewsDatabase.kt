package com.example.news.data.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.news.data.database.entity.LatestNewsEntity
import com.example.news.data.database.remote.LatestNewsDao


@Database(
    entities = [LatestNewsEntity::class],
    version = 1,
    exportSchema = true,
)
abstract class LatestNewsDatabase : RoomDatabase() {
    abstract fun newsDao(): LatestNewsDao
}