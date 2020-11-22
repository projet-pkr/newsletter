package com.mbds.newsletter.services

import com.mbds.newsletter.model.Article
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ArticleOnlineService : ArticleService {

    private val service: RetrofitApiService

    init {
        val retrofit = buildClient()
        //Init the api service with retrofit
        service = retrofit.create(RetrofitApiService::class.java)
    }
    /**
     * Configure retrofit
     */
    private fun buildClient(): Retrofit {
        val httpClient = OkHttpClient.Builder().apply {
            addLogInterceptor(this)
            addApiInterceptor(this)
        }.build()
        return Retrofit
            .Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    /**
     * Add a logger to the client so that we log every request
     */
    private fun addLogInterceptor(builder: OkHttpClient.Builder) {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        builder.addNetworkInterceptor(httpLoggingInterceptor)
    }

    /**
     * Intercept every request to the API and automatically add
     * the api key as query parameter
     */
    private fun addApiInterceptor(builder: OkHttpClient.Builder) {
        builder.addInterceptor(Interceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("apikey", apiKey)
                .build()

            val requestBuilder = original.newBuilder()
                .url(url)
            val request = requestBuilder.build()
            chain.proceed(request)
        })
    }

    override fun getArticles(): List<Article> {
        return service.list().execute().body()?.articles ?: listOf()
    }
    override fun getArticlesBySourceId(sourceId : String) : List<Article> {
        return service.getArticleBySourcesId(sourceId).execute().body()?.articles ?: listOf()
    }

    override fun getArticlesByCountry(country: String): List<Article> {
        return service.getArticleByCountry(country).execute().body()?.articles ?: listOf()
    }

    override fun getArticlesByCategory(category: String): List<Article> {
       return service.getArticleByCategory(category).execute().body()?.articles ?: listOf()
    }

    companion object {
        private const val apiKey = "0e2c97075a8f4826adfdcd85df5f1838"
        private const val apiUrl = "https://newsapi.org/v2/"
    }
}