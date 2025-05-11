package com.example.news.data.dto

import com.google.gson.annotations.SerializedName

data class Right(
    @SerializedName("article_ids")
    val articleIds: List<String>,
    @SerializedName("article_urls")
    val articleUrls: List<String>,
    @SerializedName("keywords")
    val keywords: List<Keyword>,
    @SerializedName("press_list")
    val pressList: List<String>,
    @SerializedName("summary")
    val summary: String
)