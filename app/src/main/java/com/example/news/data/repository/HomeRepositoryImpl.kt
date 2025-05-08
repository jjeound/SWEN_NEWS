package com.example.news.data.repository

import androidx.annotation.WorkerThread
import com.example.news.core.Resource
import com.example.news.data.dto.ClusterX
import com.example.news.data.model.News
import com.example.news.data.service.NewsClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val newsClient: NewsClient,
): HomeRepository{

    @WorkerThread
    override suspend fun fetchHotNews(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<ClusterX>> = flow {
        onStart()
        try {
            val response = newsClient.getHotNewsList(page = page)
            emit(response.result!!.clusters)
            onComplete()
        }catch (e: HttpException){
            onError(e.toString())
        }catch (e: IOException){
            onError(e.toString())
        }
    }

    override suspend fun fetchLatestNews(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<ClusterX>> = flow {
        onStart()
        try {
            val response = newsClient.getHotNewsList(page = page)
            emit(response.result!!.clusters)
            onComplete()
        }catch (e: HttpException){
            onError(e.toString())
        }catch (e: IOException){
            onError(e.toString())
        }
    }
}