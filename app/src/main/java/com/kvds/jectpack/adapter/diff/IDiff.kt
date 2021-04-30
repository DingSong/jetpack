package com.kvds.jectpack.adapter.diff

interface IDiff<T> {
    fun isItemTheSame(oldItem: T, newItem: T): Boolean
    fun isContentTheSame(oldItem: T, newItem: T): Boolean
}