package com.example.news.data.dto

import com.example.news.data.model.News
import com.google.gson.annotations.SerializedName

data class Cluster(
    @SerializedName("_id")
    val id: String,
    @SerializedName("bias_ratio")
    val biasRatio: BiasRatio,
    @SerializedName("bias_score")
    val biasScore: Double,
    @SerializedName("center")
    val center: Center,
    @SerializedName("cluster_id")
    val clusterId: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("left")
    val left: Left,
    @SerializedName("media_counts")
    val mediaCounts: MediaCounts,
    @SerializedName("model_ver")
    val modelVer: String,
    @SerializedName("pub_date")
    val pubDate: String,
    @SerializedName("right")
    val right: Right,
    @SerializedName("title")
    val title: String,
    @SerializedName("updated_at")
    val updatedAt: String
){
    fun toDomain(): News{
        return News(
            id = clusterId,
            title = title,
            thumbnail = null,
            left = (biasRatio.left * 100).toInt(),
            right = (biasRatio.right * 100).toInt(),
            center = (biasRatio.center * 100).toInt()
        )
    }
}

data class MediaCounts(
    @SerializedName("경향신문")
    val kyeonghang: Int?= null,
    @SerializedName("국민일보")
    val newsis: Int?= null,
    @SerializedName("동아일보")
    val donga: Int?= null,
    @SerializedName("매일경제")
    val mk: Int?= null,
    @SerializedName("민중의소리")
    val vop: Int ?= null,
    @SerializedName("세계일보")
    val segye: Int?= null,
    @SerializedName("연합뉴스")
    val yna: Int?= null,
    @SerializedName("조선일보")
    val chosun: Int? = null,
    @SerializedName("중앙일보")
    val joongang: Int?= null,
    @SerializedName("한겨레")
    val hani: Int?= null,
    @SerializedName("한국경제")
    val hankyung: Int?= null,
    @SerializedName("한국일보")
    val hankookilbo: Int?= null
)