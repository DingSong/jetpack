package com.kvds.jectpack.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.kvds.jectpack.R
import com.kvds.jectpack.model.News

class NewsAdapter(var data: List<News> = arrayListOf()) :
    RecyclerView.Adapter<NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_news, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = data[position]
        holder.itemView.findViewById<AppCompatTextView>(R.id.title).text = news.title
        holder.itemView.findViewById<AppCompatTextView>(R.id.date).text = news.date
        holder.itemView.findViewById<AppCompatTextView>(R.id.category).text = news.category
        holder.itemView.findViewById<AppCompatTextView>(R.id.authorName).text = news.authorName
    }

    override fun getItemCount() = data.size

}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)