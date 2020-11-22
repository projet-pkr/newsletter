package com.mbds.newsletter.services

import com.mbds.newsletter.model.Country
import com.mbds.newsletter.model.Source

interface SourceService {
    fun getSources() : List<Source>
    fun getCountries() : List<Country>
}