package com.example.news.data.repository

import com.example.news.data.model.NewsDetail
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    suspend fun fetchNewsDetail(
        id: Long,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
    ): Flow<NewsDetail?>
}