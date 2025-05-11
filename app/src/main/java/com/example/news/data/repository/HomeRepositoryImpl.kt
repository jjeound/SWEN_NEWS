package com.example.news.data.repository

import android.util.Log
import androidx.annotation.WorkerThread
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

class HomeRepositoryImpl @Inject constructor(
    private val newsClient: NewsClient,
    @Dispatcher(NewsAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
): HomeRepository{

    @WorkerThread
    override suspend fun fetchHotNews(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<News>> = flow {
        onStart()
        try {
            val response = newsClient.getHotNewsList(page = page)
            emit(response.result!!.clusters.map { it.toDomain() })
            Log.d("news", response.toString())
            onComplete()
        }catch (e: HttpException){
            onError(e.toString())
        }catch (e: IOException){
            onError(e.toString())
        }
    }.flowOn(ioDispatcher)

    @WorkerThread
    override suspend fun fetchLatestNews(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<News>> = flow {
        onStart()
        try {
            val response = newsClient.getLatestNewsList(page = page)
            emit(response.result!!.clusters.map { it.toDomain() })
            onComplete()
        }catch (e: HttpException){
            onError(e.toString())
        }catch (e: IOException){
            onError(e.toString())
        }
    }.flowOn(ioDispatcher)
}