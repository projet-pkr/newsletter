package com.mbds.newsletter.model

import java.util.*

class CountryDto () {
    lateinit var url : String
    lateinit var countryName : String
    lateinit var countryCode : String
    private val flagUrl = "https://www.countryflags.io/"
    private val flagSuffix = "/flat/64.png"

    companion object  {
        fun newInstance( countryCode : String) =
            CountryDto().apply {
                this.url = flagUrl + countryCode.toLowerCase() + flagSuffix
                this.countryName = Locale("", "$countryCode").displayCountry
                this.countryCode = countryCode
            }
    }
}
data class Country(val country: String)

data class CountryResponse(
    val status : String,
    val sources : List<Country>?
)