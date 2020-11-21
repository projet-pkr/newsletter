package com.mbds.newsletter.data

import com.mbds.newsletter.model.Category

class Data {
    companion object {
        fun getCategories() = listOf<Category>(
            Category("source", "Editeur", "https://picsum.photos/seed/picsum/500/300"),
            Category("category", "Categories", "https://picsum.photos/seed/picsum/500/300"),
            Category("country", "Pays", "https://picsum.photos/seed/picsum/500/300")
        )
    }
}