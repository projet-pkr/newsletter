package com.mbds.newsletter.data.article



import androidx.room.Dao
import androidx.room.Query
import com.mbds.newsletter.data.CommonDao
import com.mbds.newsletter.model.ArticleEntity

@Dao
interface ArticleDao : CommonDao<ArticleEntity> {

    @Query("SELECT * from articles")
    suspend fun getArticles() : List<ArticleEntity>

    @Query("SELECT * from articles WHERE favorite_status = :favoriteStatus")
    suspend fun getArticlesByStatus(favoriteStatus : Int ) : List<ArticleEntity>

    @Query("SELECT * from articles WHERE url = :url")
    suspend fun getArticleByUrl(url : String) : ArticleEntity

}