package com.example.news.data.repository

import com.example.news.data.dto.NewsInfo
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    suspend fun fetchNewsDetail(
        id: String,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
    ): Flow<NewsInfo?>
}