package com.kvds.jectpack.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kvds.jectpack.R
import com.kvds.jectpack.adapter.NewsAdapter
import com.kvds.jectpack.adapter.diff.diffUpdate
import com.kvds.jectpack.model.News
import com.kvds.jectpack.model.NewsType.Companion.TOP
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

        var page = 1

        btn.setOnClickListener {
            newsViewModel.fetchNews(TOP, page++)
        }

        refresher.setOnRefreshListener {
            newsViewModel.fetchNews(TOP, 1)
        }

        newsViewModel.news.observe(this) {
            refresher.isRefreshing = false
            adapter.diffUpdate(adapter.data, it)
            adapter.data = it
            if (it.isNotEmpty()) {
                newsViewModel.storeNews(it)
            }
        }

        btn1.setOnClickListener {
            newsViewModel.loadNews()
        }
        newsViewModel.newsRoom.observe(this) {
            it.forEach { item ->
                println(item.toString())
            }
        }
    }
}