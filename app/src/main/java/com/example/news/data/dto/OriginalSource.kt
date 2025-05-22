package com.example.news.data.dto

import com.google.gson.annotations.SerializedName

data class OriginalSource(
    @SerializedName("언론사")
    val name: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("제목")
    val title: String,
    @SerializedName("press_icon")
    val logo: String,
)
