package com.example.news.data.service

import com.example.news.data.dto.ApiResponse
import com.example.news.data.dto.HotNews
import javax.inject.Inject

class NewsClient @Inject constructor(
    private val newsService: NewsService
){
    suspend fun getHotNewsList(page: Int): ApiResponse<HotNews> =
        newsService.getHotNewsList(
            limit = PAGING_SIZE,
            page = page * PAGING_SIZE + 1,
        )

    companion object {
        private const val PAGING_SIZE = 10
    }
}