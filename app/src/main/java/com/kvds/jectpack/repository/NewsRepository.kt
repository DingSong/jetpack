package com.kvds.jectpack.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kvds.jectpack.api.ApiService
import com.kvds.jectpack.di.module.RepositoryModule
import com.kvds.jectpack.model.News
import com.kvds.jectpack.model.NewsData
import com.kvds.jectpack.model.NewsType
import com.kvds.jectpack.model.Response
import com.kvds.jectpack.paging.NewsPagingSource
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

@ActivityScoped
class NewsRepository @Inject constructor(
    private val apiService: ApiService,
    @Named(RepositoryModule.DISPATCHER_IO) private val dispatcher: CoroutineDispatcher
) {

    suspend fun fetchNews(type: String, page: Int, pageSize: Int): Response<NewsData> {
        return loadNewsFromNet(type, page, pageSize)
    }

    private suspend fun loadNewsFromNet(
        type: String,
        page: Int,
        pageSize: Int
    ): Response<NewsData> {
        return withContext(dispatcher) {
            apiService.fetchNews(type, page, pageSize)
        }
    }

    fun fetchNewsWithPaging(@NewsType type: String, pageSize: Int): Flow<PagingData<News>> {
        return Pager(
            config = PagingConfig(pageSize),
            pagingSourceFactory = { NewsPagingSource(apiService, type) }
        ).flow
    }

}