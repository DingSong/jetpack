package com.kvds.jectpack.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kvds.jectpack.model.News
import com.kvds.jectpack.model.NewsType
import com.kvds.jectpack.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsViewModel @ViewModelInject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    fun fetchNewsWithPaging3(@NewsType type: String, pageSize: Int = 30): Flow<PagingData<News>> {
        return repository.fetchNewsWithPaging3(type, pageSize).cachedIn(viewModelScope)
    }

}