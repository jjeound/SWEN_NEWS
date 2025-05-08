package com.example.news.data.service

import com.example.news.data.dto.ApiResponse
import com.example.news.data.dto.Cluster
import com.example.news.data.dto.HotNews
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("/clusters/hot")
    suspend fun getHotNewsList(
        @Query("limit") limit: Int = 10,
        @Query("page") page: Int = 1,
    ): ApiResponse<HotNews>
}