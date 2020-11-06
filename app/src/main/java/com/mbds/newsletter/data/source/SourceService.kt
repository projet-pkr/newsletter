package com.mbds.newsletter.data.source

import com.mbds.newsletter.model.Source

interface SourceService {
    fun getSources() : List<Source>
}