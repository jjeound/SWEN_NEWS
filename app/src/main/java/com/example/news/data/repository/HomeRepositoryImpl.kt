package com.example.news.data.repository

import androidx.annotation.WorkerThread
import com.example.news.core.Resource
import com.example.news.data.Dispatcher
import com.example.news.data.NewsAppDispatchers
import com.example.news.data.database.entity.mapper.asHotDomain
import com.example.news.data.database.entity.mapper.asHotEntity
import com.example.news.data.database.entity.mapper.asLatestDomain
import com.example.news.data.database.entity.mapper.asLatestEntity
import com.example.news.data.database.remote.HotNewsDao
import com.example.news.data.database.remote.LatestNewsDao
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
    private val hotNewsDao: HotNewsDao,
    private val latestNewsDao: LatestNewsDao,
    @Dispatcher(NewsAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
): HomeRepository{

    @WorkerThread
    override suspend fun fetchHotNews(
        page: Int,
        limit: Int,
    ): Flow<Resource<List<News>>> = flow {
        emit(Resource.Loading())
        val news = hotNewsDao.getNewsList(page).map { it.asHotDomain() }
        if (news.isEmpty()) {
            try {
                val response = newsClient.getHotNewsList(page = page, limit = limit)
                if(response.isSuccess){
                    hotNewsDao.insertNewsList(response.result!!.clusters.map { it.toDomain(page) }.map {it.asHotEntity()  })
                    emit(Resource.Success(hotNewsDao.getNewsList(page).map { it.asHotDomain() }))
                } else {
                    emit(Resource.Error(response.code))
                }
            }catch (e: HttpException){
                emit(Resource.Error(e.toString()))
            }catch (e: IOException){
                emit(Resource.Error(e.toString()))
            }
        }else{
            emit(Resource.Success(hotNewsDao.getNewsList(page).map { it.asHotDomain() }))
        }
    }.flowOn(ioDispatcher)

    @WorkerThread
    override suspend fun fetchLatestNews(
        page: Int,
        limit: Int,
    ): Flow<Resource<List<News>>> = flow {
        emit(Resource.Loading())
        val news = latestNewsDao.getNewsList(page).map { it.asLatestDomain() }
        if (news.isEmpty()) {
            try {
                val response = newsClient.getHotNewsList(page = page, limit = limit)
                if(response.isSuccess){
                    latestNewsDao.insertNewsList(response.result!!.clusters.map { it.toDomain(page) }.map {it.asLatestEntity()  })
                    emit(Resource.Success(latestNewsDao.getNewsList(page).map { it.asLatestDomain() }))
                } else {
                    emit(Resource.Error(response.code))
                }
            }catch (e: HttpException){
                emit(Resource.Error(e.toString()))
            }catch (e: IOException){
                emit(Resource.Error(e.toString()))
            }
        }else{
            emit(Resource.Success(latestNewsDao.getNewsList(page).map { it.asLatestDomain() }))
        }
    }.flowOn(ioDispatcher)
}