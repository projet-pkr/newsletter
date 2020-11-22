package com.mbds.newsletter.factory

import com.mbds.newsletter.model.Article
import com.mbds.newsletter.model.ArticleEntity
import java.time.LocalDate
import java.time.format.DateTimeFormatter
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
            article.publishedAt,
            status
        )
        fun newArticleInstance(articleEntity : ArticleEntity) = Article(
            Article.Source(
                "",
                articleEntity.source ?: "",
                ""
            ),
            articleEntity.author ?:"",
            articleEntity.title ?:"",
            articleEntity.description ?:"",
            "",
            articleEntity.url ?:"",
            articleEntity.urlToImage ?:"",
             articleEntity.publishedAt ?: ""
        )

    }
}
