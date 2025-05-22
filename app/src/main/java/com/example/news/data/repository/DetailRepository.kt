package com.example.news.data.repository

import androidx.annotation.WorkerThread
import com.example.news.core.Resource
import com.example.news.data.dto.NewsInfo
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    @WorkerThread
    suspend fun fetchNewsDetail(
        id: String,
    ): Flow<Resource<NewsInfo>>
}