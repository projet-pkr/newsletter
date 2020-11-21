package com.mbds.newsletter.model

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
    val url : String,
    val urlToImage : String,
    val publishedAt: Date
)
    : Serializable {

    data class Source(val id: String?, val name: String) : Serializable
}

