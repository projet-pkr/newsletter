package com.mbds.newsletter.data

import com.mbds.newsletter.model.Article
import retrofit2.http.GET
import retrofit2.Call

interface RetrofitApiService {

    @GET("/everything")
    fun list(): Call<List<Article>>
}