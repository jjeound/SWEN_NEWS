package com.example.news.data.dto

data class Pagination(
    val limit: Int,
    val page: Int,
    val pages: Int,
    val total: Int
)