package com.mbds.newsletter.data.article

import com.mbds.newsletter.model.ArticleEntity

class ArticleRepository (private val articleDao: ArticleDao){

    suspend fun getAllArticles() : List<ArticleEntity>{
         return articleDao.getArticles()
    }
    suspend fun getArticleByStatus(status: Int): List<ArticleEntity>{
        return articleDao.getArticlesByStatus(status)
    }
    suspend fun insertArticles( articleEntity: List<ArticleEntity>){
        articleDao.insert(articleEntity)
    }
    suspend fun getArticlesByUrl(url : String) : ArticleEntity{
        return articleDao.getArticleByUrl(url)
    }
    suspend fun updateArticle(articleEntity : ArticleEntity){
        return articleDao.update(articleEntity)
    }
    suspend fun deleteOneArticle(articleEntity: ArticleEntity){
        articleDao.deleteOne(articleEntity)
    }
}