package com.mbds.newsletter.services

import com.mbds.newsletter.model.Article

interface CountryService {
    fun getArticles(): List<Article>
}