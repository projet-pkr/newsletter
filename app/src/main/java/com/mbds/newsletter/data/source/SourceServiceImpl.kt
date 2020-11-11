package com.mbds.newsletter.data.source

import com.mbds.newsletter.factory.RetrofitApiServiceFactory
import com.mbds.newsletter.model.Country
import com.mbds.newsletter.model.Source

class SourceServiceImpl : SourceService {
    //get instance of the api
    private val retrofitApiService  = RetrofitApiServiceFactory.instance

    override fun getSources(): List<Source> {
        return retrofitApiService.getSources().execute().body()?.sources ?: listOf()
    }

    override fun getCountries(): List<Country> {
        return retrofitApiService.getCountries().execute().body()?.sources ?: listOf()
    }
}