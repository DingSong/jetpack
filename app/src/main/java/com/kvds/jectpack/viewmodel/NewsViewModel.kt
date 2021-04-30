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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel @ViewModelInject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _news = MutableLiveData<Response<List<News>>>()
    val news = _news

    private val _newsData = MutableLiveData<List<NewsData>>()
    val newsData = _newsData

    fun fetchNews() {
        viewModelScope.launch {
            _news.value = repository.fetchNews()
        }
    }

    fun loadNews() {
        viewModelScope.launch(Dispatchers.IO) {
            _newsData.postValue(newsDatabase.newsDao().getNews())
        }
    }

    fun storeNews(vararg news: NewsData) {
        viewModelScope.launch(Dispatchers.IO) {
            newsDatabase.newsDao().addNews(*news)
        }
    }

}