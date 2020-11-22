package com.mbds.newsletter.data

import com.mbds.newsletter.model.Category
import com.mbds.newsletter.model.Membre

class Data {
    companion object {
        fun getOptions() = listOf<Category>(
            Category("source", "Editeurs", "https://i.postimg.cc/9f94DdRP/editeurs.jpg"),
            Category("category", "Categories", "https://i.postimg.cc/FKNppHyL/categories.jpg"),
            Category("country", "Pays", "https://i.postimg.cc/L8TCrP6T/carte.gif")
        )

        fun getCategories() = listOf<Category>(
            Category("politics", "Politique","https://i.postimg.cc/Kc0bJ4Ch/politique2.jpg"),
            Category("business","Economie","https://i.postimg.cc/3JrXQfLD/business.jpg"),
            Category("health","Santé","https://i.postimg.cc/02n0NfYT/raoult.jpg"),
            Category("education","Education","https://i.postimg.cc/MZ3TXs6B/education.jpg"),
            Category("pandemic","Pandemie","https://i.postimg.cc/mk1GPyj3/pandemie.jpg"),
            Category("sciences","Sciences","https://i.postimg.cc/9Xy2HJ4V/sciences.jpg"),
            Category("ecology","Ecologie","https://i.postimg.cc/J7Nf20gz/ecologie.jpg"),
            Category("sports","Sport","https://i.postimg.cc/tgScVbhH/sport.jpg"),
            Category("technology","Technologie","https://i.postimg.cc/htwxJ3f5/technology.jpg")
        )

        fun getMembres() = listOf<Membre>(
            Membre("LOUBOTA", "Koubikani"),
            Membre("RANDRIANANDRAINA ", "Manotiana"),
            Membre("PAJANY ", "Allan")
        )

        fun getFonctionnalites() = listOf<String>(
            "Details d'un article et redirection vers l'article complet",
            "Ajout dans les favoris"
        )
    }
}