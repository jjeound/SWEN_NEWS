package com.example.news.data.repository

import com.example.news.data.model.News
import com.example.news.data.service.NewsClient
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoreRepositoryImpl @Inject constructor(
    private val newsClient: NewsClient,
): MoreRepository {
    override suspend fun fetchSingleCategoryHotNews(
        category: String,
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<News>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchSingleCategoryLatestNews(
        category: String,
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<News>> {
        TODO("Not yet implemented")
    }
}