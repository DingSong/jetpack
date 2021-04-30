package com.kvds.jectpack.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kvds.jectpack.common.BaseApplication
import com.kvds.jectpack.db.dao.NewsDao
import com.kvds.jectpack.model.NewsData

@Database(entities = [NewsData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        val newsDatabase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            Room.databaseBuilder(
                BaseApplication.instance.applicationContext,
                AppDatabase::class.java,
                "database-news"
            ).build()
        }
    }

}