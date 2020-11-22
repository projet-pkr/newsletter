package com.mbds.newsletter

import android.app.Application
import android.content.Context
import com.mbds.newsletter.data.NewsletterRoomDatabase
import com.mbds.newsletter.data.article.ArticleRepository

class NewsletterApplication : Application() {

    val database by lazy {NewsletterRoomDatabase.getDatabase(this)}
    val articleRepository by lazy {ArticleRepository(database.articleDao())}

    fun getRepositoryInstance(): ArticleRepository {
        return articleRepository
    }

    companion object {
        fun getRepository(context: Context): ArticleRepository {
            val database = NewsletterRoomDatabase.getDatabase(context)
            return ArticleRepository(database.articleDao())
        }
    }
}