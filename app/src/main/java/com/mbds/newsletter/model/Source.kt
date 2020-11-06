package com.mbds.newsletter.model


data class SourceResponse(
    val status : String,
    val sources : List<Source>?
)
data class Source(val id : String, val name: String, val description: String)