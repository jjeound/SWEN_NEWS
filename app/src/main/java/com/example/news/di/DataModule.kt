package com.example.news.di

import com.example.news.data.repository.DetailRepository
import com.example.news.data.repository.DetailRepositoryImpl
import com.example.news.data.repository.HomeRepository
import com.example.news.data.repository.HomeRepositoryImpl
import com.example.news.data.repository.MoreRepository
import com.example.news.data.repository.MoreRepositoryImpl
import com.example.news.data.service.NewsClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideHomeRepository(client: NewsClient): HomeRepository{
        return HomeRepositoryImpl(client)
    }

    @Provides
    @Singleton
    fun provideMoreRepository(client: NewsClient): MoreRepository{
        return MoreRepositoryImpl(client)
    }

    @Provides
    @Singleton
    fun provideDetailRepository(client: NewsClient): DetailRepository{
        return DetailRepositoryImpl(client)
    }
}