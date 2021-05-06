package com.kvds.jectpack.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kvds.jectpack.R
import com.kvds.jectpack.adapter.NewsAdapter
import com.kvds.jectpack.adapter.diff.diffUpdate
import com.kvds.jectpack.model.News
import com.kvds.jectpack.repository.NewsRepository
import com.kvds.jectpack.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_news.*
import javax.inject.Inject

/**
 * LiveData + Paging + Retrofit + Room + Hilt + Coroutine
 */

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    private val newsViewModel: NewsViewModel by viewModels()

    @Inject
    lateinit var repository: NewsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val adapter = NewsAdapter()
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)

        btn.setOnClickListener {
            newsViewModel.fetchNews()
        }

        newsViewModel.news.observe(this) {
            val result = it.data?.data as? MutableList<News> ?: arrayListOf()
            adapter.diffUpdate(adapter.data, result)
            adapter.data = result
            it.data?.apply {
                newsViewModel.storeNews(this)
            }
        }

        btn1.setOnClickListener {
            newsViewModel.loadNews()
        }
        newsViewModel.newsData.observe(this) {
            it.forEach { item ->
                println(item.toString())
            }
        }
    }
}