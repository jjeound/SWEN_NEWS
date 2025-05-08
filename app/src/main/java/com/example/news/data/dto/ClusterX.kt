package com.example.news.data.dto

data class ClusterX(
    val _id: String,
    val bias_ratio: BiasRatio,
    val bias_score: Double,
    val center: Center,
    val cluster_id: String,
    val created_at: String,
    val left: Left,
    val media_counts: MediaCounts,
    val model_ver: String,
    val pub_date: String,
    val right: Right,
    val title: String,
    val updated_at: String
)