package com.kvds.jectpack.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvds.jectpack.db.AppDatabase.Companion.newsDatabase
import com.kvds.jectpack.model.News
import com.kvds.jectpack.model.NewsData
import com.kvds.jectpack.model.Response
import com.kvds.jectpack.repository.NewsRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel @ViewModelInject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _news = MutableLiveData<MutableList<News>>()
    val news = _news

    private val _newsRoom = MutableLiveData<List<News>>()
    val newsRoom = _newsRoom

    fun fetchNews(type: String, page: Int, pageSize: Int = 30) {
        viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->
            println(throwable.message)
        }) {
            _news.value = repository.fetchNews(type, page, pageSize).data?.data ?: arrayListOf()
        }
    }

    fun loadNews() {
        viewModelScope.launch(Dispatchers.IO) {
            _newsRoom.postValue(newsDatabase.newsDao().getAllNews())
        }
    }

    fun storeNews(news: List<News>) {
        viewModelScope.launch(Dispatchers.IO) {
            newsDatabase.newsDao().addNews(news)
        }
    }

}