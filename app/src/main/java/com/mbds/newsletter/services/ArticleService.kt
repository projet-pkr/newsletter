package com.mbds.newsletter.services

import com.mbds.newsletter.model.Article

interface ArticleService {
    fun getArticles(): List<Article>
    fun getArticlesBySourceId(sourceId : String) : List<Article>
    fun getArticlesByCountry(country : String) : List<Article>
    fun getArticlesByCategory(category : String) : List<Article>
}