package com.mbds.newsletter.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import java.io.Serializable

import java.util.*

data class ArticleResponse(
    val status : String,
    val totalResults: Int?,
    val articles : List<Article>?
)

data class Article(
    val source: Source,
    val author: String,
    val title : String,
    val description: String,
    val content: String,
    val url : String,
    val urlToImage : String,
    val publishedAt: String

): Serializable { data class Source(val id : String,
                                    val name: String,
                                    val description: String) : Serializable }

@Entity(tableName = "articles")
data class ArticleEntity(
    val source : String?,
    val author: String?,
    val title : String?,
    val description: String?,
    @PrimaryKey val url : String,
    val urlToImage : String?,
    val publishedAt: String?,
    @ColumnInfo(name = "favorite_status") var favoriteStatus : Int
)
class ArticleDto(){

}
