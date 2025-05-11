package com.example.news.data.dto

data class HotNewsResponse(
    val clusters: List<Cluster>,
    val pagination: Pagination
)