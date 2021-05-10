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
    val id: Int? = null,

    @ColumnInfo(name = "state")
    var stat: Int,

    var page: Int,

    var pageSize: Int,

    var data: MutableList<News>
)

@Entity(tableName = "news")
class News(
    @Ignore
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

    override fun toString(): String {
        return "News(uniqueKey='$uniqueKey', title='$title', date='$date', category='$category', authorName='$authorName', url='$url', thumbnail01='$thumbnail01', thumbnail02='$thumbnail02', thumbnail03='$thumbnail03', isContent=$isContent)"
    }

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