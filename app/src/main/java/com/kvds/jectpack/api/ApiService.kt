package com.kvds.jectpack.api

import com.kvds.jectpack.common.Constants.APP_KEY
import com.kvds.jectpack.model.News
import com.kvds.jectpack.model.NewsData
import com.kvds.jectpack.model.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/toutiao/index")
    suspend fun fetchNews(
        @Query("type") type: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int = 30,
        @Query("key") key: String = APP_KEY
    ): Response<NewsData>
}