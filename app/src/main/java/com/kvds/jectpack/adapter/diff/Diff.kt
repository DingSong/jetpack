package com.kvds.jectpack.adapter.diff

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

fun <T : RecyclerView.ViewHolder, K : IDiff<K>> RecyclerView.Adapter<T>.diffUpdate(
    old: List<K>,
    new: List<K>,
) {
    DiffUtil.calculateDiff(object : DiffUtil.Callback() {

        override fun getOldListSize() = old.size

        override fun getNewListSize() = new.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return old[oldItemPosition].isItemTheSame(old[oldItemPosition], new[newItemPosition])
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return old[oldItemPosition].isContentTheSame(new[newItemPosition], new[newItemPosition])
        }
    }).dispatchUpdatesTo(this)
}