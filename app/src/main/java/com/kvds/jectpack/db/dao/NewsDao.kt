package com.kvds.jectpack.db.dao

import androidx.room.*
import com.kvds.jectpack.model.News
import com.kvds.jectpack.model.NewsData

@Dao
interface NewsDao {

    // news
    @Query("SELECT * FROM news WHERE uniqueKey = :uniqueKey")
    fun getNewsByUniqueKey(uniqueKey: Int): News

    @Query("SELECT * FROM news")
    fun getAllNews(): List<News>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNews(news: List<News>)

    @Delete
    fun delete(vararg news: News)

    // newsData
    @Query("SELECT * FROM newsData WHERE id = :id")
    fun getNewsDataById(id: Int): NewsData

    @Query("SELECT * FROM newsData")
    fun getAllNewsData(): List<NewsData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNewsData(newsData: List<NewsData>)

    @Delete
    fun delete(vararg newsData: NewsData)

}