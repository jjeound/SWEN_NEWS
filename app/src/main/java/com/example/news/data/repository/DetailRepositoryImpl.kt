package com.example.news.data.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.news.data.Dispatcher
import com.example.news.data.NewsAppDispatchers
import com.example.news.data.dto.NewsInfo
import com.example.news.data.service.NewsClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val newsClient: NewsClient,
    @Dispatcher(NewsAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
): DetailRepository {

    @WorkerThread
    override suspend fun fetchNewsDetail(
        id: String,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<NewsInfo?> = flow {
        try {
            Log.d("fetch", "fetched: $id")
            val response = newsClient.getNewsDetail(id = id)
            if (response.isSuccess){
                emit(response.result)
                Log.d("newsInfo", response.toString())
                onComplete()
            }else{
                Log.d("error info", response.code)
                onError(response.message)
            }
        }catch (e: HttpException){
            onError(e.message)
        }catch (e: IOException){
            onError(e.message)
        }
    }.flowOn(ioDispatcher)

}