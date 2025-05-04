package com.example.news.data.repository

import com.example.news.data.model.News
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun fetchHotNews(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
    ): Flow<List<News>>
    suspend fun fetchLatestNews(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
    ): Flow<List<News>>
}