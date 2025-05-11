package com.example.news.data.dto

data class NewsResponse(
    val clusters: List<Cluster>,
    val pagination: Pagination
)