package com.kvds.jectpack.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kvds.jectpack.db.AppDatabase.Companion.newsDatabase
import com.kvds.jectpack.model.News
import com.kvds.jectpack.model.NewsData
import com.kvds.jectpack.model.NewsType
import com.kvds.jectpack.model.Response
import com.kvds.jectpack.repository.NewsRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NewsViewModel @ViewModelInject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    fun fetchNewsWithPaging(@NewsType type: String, pageSize: Int): Flow<PagingData<News>> {
        return repository.fetchNewsWithPaging(type, pageSize).cachedIn(viewModelScope)
    }

}