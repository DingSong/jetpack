package com.kvds.jectpack.model

import androidx.room.*
import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.kvds.jectpack.adapter.diff.IDiff
import com.kvds.jectpack.adapter.diff.NewsDiffDelegate

@Entity(tableName = "newsData")
@TypeConverters(NewsDataConverter::class)
data class NewsData(

    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "state")
    var stat: Int,

    var page: Int,

    var pageSize: Int,

    var data: List<News>
) {
    override fun toString(): String {
        return "NewsData(id=$id, stat=$stat, page=$page, pageSize=$pageSize, data=$data)"
    }
}

class News(
    @Expose(
        serialize = false,
        deserialize = false
    ) private val delegate: IDiff<News> = NewsDiffDelegate()
) :
    IDiff<News> by delegate {

    @SerializedName("uniquekey")
    var uniqueKey: String = ""

    var title: String = ""

    var date: String = ""

    var category: String = ""

    @SerializedName("author_name")
    var authorName: String = ""

    var url: String = ""

    @SerializedName("thumbnail_pic_s")
    var thumbnail01: String = ""

    @SerializedName("thumbnail_pic_s02")
    var thumbnail02: String = ""

    @SerializedName("thumbnail_pic_s03")
    var thumbnail03: String = ""

    @SerializedName("is_content")
    var isContent: Boolean = false
}

class NewsDataConverter {

    @TypeConverter
    fun stringToObject(value: String?): List<News>? {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
            .fromJson(value, object : TypeToken<List<News>>() {}.type)
    }

    @TypeConverter
    fun objectToString(value: List<News>?): String? {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(value)
    }
}