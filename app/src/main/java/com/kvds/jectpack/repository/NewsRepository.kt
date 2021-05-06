package com.kvds.jectpack.repository

import com.kvds.jectpack.api.ApiService
import com.kvds.jectpack.common.Constants.BASE_URL
import com.kvds.jectpack.di.module.RepositoryModule
import com.kvds.jectpack.model.NewsData
import com.kvds.jectpack.model.Response
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Named

interface IRepository<T> {
    suspend fun fetchNews(): T
}

@ActivityScoped
class NewsRepository @Inject constructor(@Named(RepositoryModule.DISPATCHER_IO) private val dispatcher: CoroutineDispatcher) :
    IRepository<Response<NewsData>> {

    override suspend fun fetchNews(): Response<NewsData> {
        return loadNewsFromNet()
    }

    private suspend fun loadNewsFromNet(): Response<NewsData> {
        return withContext(dispatcher) {
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
            retrofit.create(ApiService::class.java).getNews()
        }
    }
}