package com.example.news.data.repository

import androidx.annotation.WorkerThread
import com.example.news.core.Resource
import com.example.news.data.dto.ClusterX
import com.example.news.data.dto.HotNews
import com.example.news.data.model.News
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    @WorkerThread
    suspend fun fetchHotNews(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
    ): Flow<List<ClusterX>>

    suspend fun fetchLatestNews(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
    ): Flow<List<ClusterX>>
}