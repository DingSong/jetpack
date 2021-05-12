package com.kvds.jectpack.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kvds.jectpack.api.ApiService
import com.kvds.jectpack.model.News
import com.kvds.jectpack.model.NewsType
import javax.inject.Inject

class NewsPagingSource @Inject constructor(
    private val apiService: ApiService,
    @NewsType private val type: String
) : PagingSource<Int, News>() {

    override fun getRefreshKey(state: PagingState<Int, News>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> {
        val page = params.key ?: 1
        val pageSize = params.loadSize
        val response = apiService.fetchNews(type, page, pageSize)
        val newsItems = response.data?.data ?: arrayListOf()
        val prevKey = if (page > 1) page - 1 else null
        val nextKey = if (newsItems.isNotEmpty()) page + 1 else null
        return LoadResult.Page(newsItems, prevKey, nextKey)
    }
}