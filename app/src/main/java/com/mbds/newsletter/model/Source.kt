package com.mbds.newsletter.model

data class SourceResponse(
    val status : String,
    val sources : List<Source>?
)

//https://newsapi.org/docs/endpoints/sources
data class Source(
    val id : String,
    val name: String,
    val description: String
)