package com.example.news.data.database.entity.mapper

interface EntityMapper<Domain, Entity> {

    fun asEntity(domain: Domain, category: Boolean): Entity

    fun asDomain(entity: Entity): Domain
}