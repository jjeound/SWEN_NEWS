package com.example.news.data.model

data class News(
    val id: Long,
    val title: String,
    val thumbnail: String,
    val left: Int,
    val right: Int,
    val center: Int
)
