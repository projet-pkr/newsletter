package com.mbds.newsletter.data

import com.mbds.newsletter.model.Article

interface ArticleService {
    fun getArticles(): List<Article>
}