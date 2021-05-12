package com.kvds.jectpack.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kvds.jectpack.R
import com.kvds.jectpack.model.News
import java.io.File
import javax.inject.Inject

class NewsAdapter @Inject constructor() : PagingDataAdapter<News, NewsViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem.isItemTheSame(oldItem, newItem)
            }

            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem.isContentTheSame(oldItem, newItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_news, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = getItem(position)
        holder.itemView.findViewById<AppCompatTextView>(R.id.title).text = news?.title
        holder.itemView.findViewById<AppCompatTextView>(R.id.date).text = news?.date
        holder.itemView.findViewById<AppCompatTextView>(R.id.category).text = news?.category
        holder.itemView.findViewById<AppCompatTextView>(R.id.authorName).text = news?.authorName
    }

}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)