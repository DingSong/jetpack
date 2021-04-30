package com.kvds.jectpack.db.dao

import androidx.room.*
import com.kvds.jectpack.model.NewsData

@Dao
interface NewsDao {

    @Query("SELECT * FROM newsData WHERE id = :id")
    fun getNewsById(id: Int): NewsData

    @Query("SELECT * FROM newsData")
    fun getNews(): List<NewsData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNews(vararg news: NewsData)

    @Delete
    fun delete(news: NewsData)

}