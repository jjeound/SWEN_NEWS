package com.example.news.data.model

data class News(
    val id: String,
    val title: String,
    val thumbnail: String? = null,
    val left: Int,
    val right: Int,
    val center: Int
)
