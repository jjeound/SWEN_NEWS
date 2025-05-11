package com.example.news.di

import com.example.news.data.repository.DetailRepository
import com.example.news.data.repository.DetailRepositoryImpl
import com.example.news.data.repository.HomeRepository
import com.example.news.data.repository.HomeRepositoryImpl
import com.example.news.data.repository.MoreRepository
import com.example.news.data.repository.MoreRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @Binds
    fun bindsHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

    @Binds
    fun bindsMoreRepository(moreRepositoryImpl: MoreRepositoryImpl): MoreRepository

    @Binds
    fun bindsDetailRepository(detailRepositoryImpl: DetailRepositoryImpl): DetailRepository
}
