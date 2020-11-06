package com.mbds.newsletter.data

import com.mbds.newsletter.model.ArticleResponse
import com.mbds.newsletter.model.SourceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApiService {

    @GET("everything?q=bitcoin")
    fun list(): Call<ArticleResponse>

    @GET("sources")
    fun getSources() : Call<SourceResponse>

    @GET("everything")
    fun getArticleBySourcesId(@Query("sources") sourceId: String) : Call<ArticleResponse>
}