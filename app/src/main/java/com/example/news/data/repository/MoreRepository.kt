package com.example.news.data.repository

import androidx.annotation.WorkerThread
import androidx.paging.PagingData
import com.example.news.data.database.entity.HotNewsEntity
import com.example.news.data.database.entity.LatestNewsEntity
import kotlinx.coroutines.flow.Flow

interface MoreRepository {
    @WorkerThread
    suspend fun fetchHotNews(
        category: String? = null
    ): Flow<PagingData<HotNewsEntity>>

    @WorkerThread
    suspend fun fetchLatestNews(
        category: String? = null
    ): Flow<PagingData<LatestNewsEntity>>
}