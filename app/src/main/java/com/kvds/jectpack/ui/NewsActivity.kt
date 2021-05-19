package com.kvds.jectpack.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.kvds.jectpack.R
import com.kvds.jectpack.adapter.NewsAdapter
import com.kvds.jectpack.model.NewsType
import com.kvds.jectpack.repository.NewsRepository
import com.kvds.jectpack.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * LiveData + Paging + Retrofit + Room + Hilt + Coroutine
 */

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    private val newsViewModel: NewsViewModel by viewModels()

    @Inject
    lateinit var repository: NewsRepository

    @Inject
    lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        adapter.addLoadStateListener {
            println("onLoadStateChanged")
            when (it.refresh) {
                is LoadState.NotLoading -> {
                    refresher.isRefreshing = false
                }
                is LoadState.Loading -> {
                    refresher.isRefreshing = true
                }
                is LoadState.Error -> {
                    refresher.isRefreshing = false
                    Snackbar.make(
                        refresher,
                        "error message : ${(it.refresh as LoadState.Error).error.message ?: ""}",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            runCatching {
                newsViewModel.fetchNewsWithPaging3(NewsType.TOP).collect {
                    adapter.submitData(it)
                }
            }.onSuccess {
                refresher.isRefreshing = false
            }.onFailure {
                Snackbar.make(
                    refresher,
                    "error message : ${it.message ?: ""}",
                    Snackbar.LENGTH_SHORT
                ).show()
                refresher.isRefreshing = false
            }
        }

        refresher.setOnRefreshListener {
            adapter.refresh()
        }
    }
}