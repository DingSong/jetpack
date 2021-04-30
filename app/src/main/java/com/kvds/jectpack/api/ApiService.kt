package com.kvds.jectpack.api

import com.kvds.jectpack.common.Constants.APP_KEY
import com.kvds.jectpack.model.News
import com.kvds.jectpack.model.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/toutiao/index")
    suspend fun getNews(
        @Query("type") type: String = "top",
        @Query("key") key: String = APP_KEY
    ): Response<List<News>>
}