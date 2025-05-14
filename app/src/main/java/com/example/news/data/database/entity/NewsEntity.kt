package com.example.news.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsEntity(
    @PrimaryKey val id: String,
    val title: String,
    val thumbnail: String? = null,
    val left: Int,
    val right: Int,
    val center: Int,
    val page: Int,
    val category: Boolean
)