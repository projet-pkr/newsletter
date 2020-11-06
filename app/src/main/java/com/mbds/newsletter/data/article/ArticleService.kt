package com.mbds.newsletter.data.article

import com.mbds.newsletter.model.Article

interface ArticleService {
    fun getArticles(): List<Article>
    fun getArticlesBySourceId(sourceId : String) : List<Article>
}