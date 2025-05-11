package com.example.news.data.service

import com.example.news.data.dto.ApiResponse
import com.example.news.data.dto.NewsResponse
import com.example.news.data.dto.NewsInfo
import javax.inject.Inject

class NewsClient @Inject constructor(
    private val newsService: NewsService
){
    suspend fun getHotNewsList(page: Int): ApiResponse<NewsResponse> =
        newsService.getHotNewsList(
            limit = PAGING_SIZE_SMALL,
            page = page * PAGING_SIZE + 1,
        )

    suspend fun getLatestNewsList(page: Int): ApiResponse<NewsResponse> =
        newsService.getLatestNewsList(
            limit = PAGING_SIZE_SMALL,
            page = page * PAGING_SIZE + 1,
        )
    suspend fun getCategoryHotNewsList(category: String, page: Int): ApiResponse<NewsResponse> =
        newsService.getCategoryHotNewsList(
            category = category,
            limit = PAGING_SIZE,
            page = page * PAGING_SIZE + 1,
        )
    suspend fun getCategoryLatestNewsList(category: String, page: Int): ApiResponse<NewsResponse> =
        newsService.getCategoryLatestNewsList(
            category = category,
            limit = PAGING_SIZE,
            page = page * PAGING_SIZE + 1,
        )
    suspend fun getNewsDetail(id: String): ApiResponse<NewsInfo> =
        newsService.getNewsDetail(id = id)

    companion object {
        private const val PAGING_SIZE_SMALL = 5
        private const val PAGING_SIZE = 10
    }
}