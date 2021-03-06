package com.mbds.newsletter.services

import com.mbds.newsletter.factory.RetrofitApiServiceFactory
import com.mbds.newsletter.model.Country
import com.mbds.newsletter.model.Source
import com.mbds.newsletter.services.SourceService

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