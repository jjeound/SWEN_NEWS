package com.example.news.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.news.data.database.entity.NewsEntity

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsList(newsList: List<NewsEntity>)

    @Query("SELECT * FROM newsentity WHERE page = :page_ and category = :category_")
    suspend fun getNewsList(page_: Int, category_: Boolean): List<NewsEntity>

}