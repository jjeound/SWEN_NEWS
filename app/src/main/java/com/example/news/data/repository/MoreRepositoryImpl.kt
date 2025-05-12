package com.example.news.data.repository

import com.example.news.data.Dispatcher
import com.example.news.data.NewsAppDispatchers
import com.example.news.data.model.News
import com.example.news.data.service.NewsClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MoreRepositoryImpl @Inject constructor(
    private val newsClient: NewsClient,
    @Dispatcher(NewsAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
): MoreRepository {
    override suspend fun fetchSingleCategoryHotNews(
        category: String,
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<News>> = flow {
        onStart()
        try {
            val response = newsClient.getCategoryHotNewsList(category = category ,page = page)
            emit(response.result!!.clusters.map { it.toDomain(0) })
            onComplete()
        }catch (e: HttpException){
            onError(e.toString())
        }catch (e: IOException){
            onError(e.toString())
        }
    }.flowOn(ioDispatcher)

    override suspend fun fetchSingleCategoryLatestNews(
        category: String,
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<News>> = flow {
        onStart()
        try {
            val response = newsClient.getCategoryLatestNewsList(category = category ,page = page)
            emit(response.result!!.clusters.map { it.toDomain(0) })
            onComplete()
        }catch (e: HttpException){
            onError(e.toString())
        }catch (e: IOException){
            onError(e.toString())
        }
    }.flowOn(ioDispatcher)
}