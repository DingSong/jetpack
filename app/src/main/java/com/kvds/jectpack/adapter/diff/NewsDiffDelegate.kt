package com.kvds.jectpack.adapter.diff

import com.kvds.jectpack.model.News

class NewsDiffDelegate : IDiff<News> {

    override fun isItemTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.uniqueKey == newItem.uniqueKey
    }

    override fun isContentTheSame(oldItem: News, newItem: News): Boolean {
        return when {
                    oldItem.url == newItem.url ||
                    oldItem.authorName == newItem.authorName ||
                    oldItem.category == newItem.category ||
                    oldItem.date == newItem.date ||
                    oldItem.thumbnail01 == newItem.thumbnail01 ||
                    oldItem.thumbnail02 == newItem.thumbnail02 ||
                    oldItem.thumbnail03 == newItem.thumbnail03 ||
                    oldItem.isContent == newItem.isContent ||
                    oldItem.title == newItem.title -> true
            else -> false
        }
    }
}