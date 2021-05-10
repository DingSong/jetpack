package com.kvds.jectpack.repository

import com.kvds.jectpack.api.ApiService
import com.kvds.jectpack.common.Constants.BASE_URL
import com.kvds.jectpack.di.module.RepositoryModule
import com.kvds.jectpack.model.NewsData
import com.kvds.jectpack.model.Response
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Named

@ActivityScoped
class NewsRepository @Inject constructor(@Named(RepositoryModule.DISPATCHER_IO) private val dispatcher: CoroutineDispatcher) {

    suspend fun fetchNews(type: String, page: Int, pageSize: Int): Response<NewsData> {
        return loadNewsFromNet(type, page, pageSize)
    }

    private suspend fun loadNewsFromNet(
        type: String,
        page: Int,
        pageSize: Int
    ): Response<NewsData> {
        return withContext(dispatcher) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create()).build()
            retrofit.create(ApiService::class.java).getNews(type, page, pageSize)
        }
    }
}