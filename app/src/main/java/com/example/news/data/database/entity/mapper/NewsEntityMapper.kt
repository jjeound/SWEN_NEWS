package com.example.news.data.database.entity.mapper

import com.example.news.data.database.entity.NewsEntity
import com.example.news.data.model.News

object NewsEntityMapper : EntityMapper<List<News>, List<NewsEntity>> {

    override fun asEntity(domain: List<News>, category: Boolean): List<NewsEntity> {
        return domain.map { news ->
            NewsEntity(
                id = news.id,
                title = news.title,
                thumbnail = news.thumbnail,
                left = news.left,
                right = news.right,
                center = news.center,
                page = news.page,
                category = category
            )
        }
    }

    override fun asDomain(entity: List<NewsEntity>): List<News> {
        return entity.map { newsEntity ->
            News(
                id = newsEntity.id,
                title = newsEntity.title,
                thumbnail = newsEntity.thumbnail,
                left = newsEntity.left,
                right = newsEntity.right,
                center = newsEntity.center,
                page = newsEntity.page,
            )
        }
    }
}

fun List<News>.asEntity(category: Boolean): List<NewsEntity> {
    return NewsEntityMapper.asEntity(this, category)
}

fun List<NewsEntity>?.asDomain(): List<News> {
    return NewsEntityMapper.asDomain(this.orEmpty())
}