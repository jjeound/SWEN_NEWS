package com.example.news.data.repository

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
            val response = newsClient.getNewsDetail(id = id)
            emit(response.result)
            onComplete()
        }catch (e: HttpException){
            onError(e.message)
        }catch (e: IOException){
            onError(e.message)
        }
    }.flowOn(ioDispatcher)

}