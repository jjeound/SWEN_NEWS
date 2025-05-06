package com.example.news.data.repository

import com.example.news.data.model.News
import kotlinx.coroutines.flow.Flow

interface MoreRepository {
    suspend fun fetchSingleCategoryHotNews(
        category: String,
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
    ): Flow<List<News>>
    suspend fun fetchSingleCategoryLatestNews(
        category: String,
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
    ): Flow<List<News>>
}