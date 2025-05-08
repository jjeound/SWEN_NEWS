package com.example.news.data.dto

data class Left(
    val article_ids: List<String>,
    val article_urls: List<String>,
    val keywords: List<Keyword>,
    val press_list: List<String>,
    val summary: String
)