package com.mbds.newsletter.data.countries

import com.mbds.newsletter.model.Article

interface CountryService {
    fun getArticles(): List<Article>
}