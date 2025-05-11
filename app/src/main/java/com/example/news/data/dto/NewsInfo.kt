package com.example.news.data.dto

import com.google.gson.annotations.SerializedName

data class NewsInfo(
    @SerializedName("article_ids")
    val articleIds: List<String>,
    @SerializedName("article_urls")
    val articleUrls: List<String>,
    @SerializedName("bias_ratio")
    val biasRatio: BiasRatio,
    val center: Center?,
    @SerializedName("created_at")
    val createdAt: String,
    val left: Left?,
    @SerializedName("media_counts")
    val mediaCounts: MediaCounts,
    @SerializedName("model_ver")
    val modelVer: String,
    @SerializedName("press_list")
    val pubDate: String,
    val right: Right?,
    val title: String,
    @SerializedName("updated_at")
    val updatedAt: String
)