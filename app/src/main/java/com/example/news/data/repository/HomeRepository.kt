package com.example.news.data.repository

import androidx.annotation.WorkerThread
import com.example.news.core.Resource
import com.example.news.data.model.News
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    @WorkerThread
    suspend fun fetchHotNews(
        page: Int,
        limit: Int,
    ): Flow<Resource<List<News>>>

    suspend fun fetchLatestNews(
        page: Int,
        limit: Int,
    ): Flow<Resource<List<News>>>
}