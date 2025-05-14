package com.example.news.data.repository

import androidx.annotation.WorkerThread
import com.example.news.core.Resource
import com.example.news.data.Dispatcher
import com.example.news.data.NewsAppDispatchers
import com.example.news.data.database.dao.NewsDao
import com.example.news.data.database.entity.mapper.asDomain
import com.example.news.data.database.entity.mapper.asEntity
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
    private val dao: NewsDao,
    @Dispatcher(NewsAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
): HomeRepository{

    @WorkerThread
    override suspend fun fetchHotNews(
        page: Int,
        limit: Int,
    ): Flow<Resource<List<News>>> = flow {
        emit(Resource.Loading())
        val news = dao.getNewsList(page, true).asDomain()
        if (news.isEmpty()) {
            try {
                val response = newsClient.getHotNewsList(page = page, limit = limit)
                if(response.isSuccess){
                    dao.insertNewsList(response.result!!.clusters.map { it.toDomain(page) }.asEntity(true))
                    emit(Resource.Success(dao.getNewsList(page, true).asDomain()))
                } else {
                    emit(Resource.Error(response.code))
                }
            }catch (e: HttpException){
                emit(Resource.Error(e.toString()))
            }catch (e: IOException){
                emit(Resource.Error(e.toString()))
            }
        }else{
            emit(Resource.Success(dao.getNewsList(page, true).asDomain()))
        }
    }.flowOn(ioDispatcher)

    @WorkerThread
    override suspend fun fetchLatestNews(
        page: Int,
        limit: Int,
    ): Flow<Resource<List<News>>> = flow {
        emit(Resource.Loading())
        val news = dao.getNewsList(page, false).asDomain()
        if (news.isEmpty()) {
            try {
                val response = newsClient.getLatestNewsList(page = page, limit = limit)
                if(response.isSuccess){
                    dao.insertNewsList(response.result!!.clusters.map { it.toDomain(page) }.asEntity(false))
                    emit(Resource.Success(dao.getNewsList(page, false).asDomain()))
                } else {
                    emit(Resource.Error(response.code))
                }
            }catch (e: HttpException){
                emit(Resource.Error(e.toString()))
            }catch (e: IOException){
                emit(Resource.Error(e.toString()))
            }
        }else{
            emit(Resource.Success(dao.getNewsList(page, false).asDomain()))
        }
    }.flowOn(ioDispatcher)
}