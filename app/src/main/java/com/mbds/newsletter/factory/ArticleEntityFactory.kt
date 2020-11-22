package com.mbds.newsletter.factory

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.mbds.newsletter.model.Article
import com.mbds.newsletter.model.ArticleEntity
import com.mbds.newsletter.model.Source
import java.util.*

class ArticleEntityFactory {

    companion object {
        fun newInstance(article : Article, status : Int = 0) = ArticleEntity(
            article.source.name,
            article.author,
            article.title,
            article.description,
            article.url,
            article.urlToImage,
            article.publishedAt.toString(),
            status
        )
    }
}
