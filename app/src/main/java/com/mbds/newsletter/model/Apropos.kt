package com.mbds.newsletter.model

data class Apropos (
    val membres : List<Membre>?,
    val urlRepo : String,
    val libs : List<String>?,
    val fonctionnalites : List<String>

)data class Membre(val nom: String, val prenom: String)