package com.example.news.data.repository

import com.example.news.data.model.News
import com.example.news.data.model.NewsDetail
import com.example.news.data.service.NewsClient
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val newsClient: NewsClient,
): DetailRepository {
    override suspend fun fetchNewsDetail(
        id: Long,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<NewsDetail?> {
        TODO("Not yet implemented")
    }
}