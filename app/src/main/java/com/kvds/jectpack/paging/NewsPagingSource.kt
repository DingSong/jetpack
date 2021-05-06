//package com.kvds.jectpack.paging
//
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.kvds.jectpack.model.News
//import com.kvds.jectpack.model.Response
//
//class NewsPagingSource: PagingSource<Int, Response<List<News>>>() {
//    override fun getRefreshKey(state: PagingState<Int, Response<List<News>>>): Int? {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Response<List<News>>> {
//        TODO("Not yet implemented")
//    }
//}