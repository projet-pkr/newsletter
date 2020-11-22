package com.mbds.newsletter

import android.content.Intent
import android.net.Uri
import android.os.Bundle

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.Fragment
import com.mbds.newsletter.NewsletterApplication.Companion.getRepository
import com.mbds.newsletter.fragments.ArticlesFragment
import com.mbds.newsletter.fragments.CategoriesFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        changeFragment(CategoriesFragment())
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    } 

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_a_propos -> {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://github.com/projet-pkr/newsletter")
            intent.setPackage("com.android.chrome") // package of SafeBrowser App

            startActivity(intent)
            true
        }

        R.id.action_favoris -> {
            // User chose the "Favorite" action, mark the current item
            // as a favorite...
           changeFragment(ArticlesFragment.newInstance(displayFavorite = "displayFavorite"))
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }

    }
}

fun MainActivity.changeFragment(fragment: Fragment) {
    val applicationFromMain = this.application
    var frag : Fragment
    if(fragment is ArticlesFragment){
      frag =  fragment.apply {
                this.application = applicationFromMain
                this.articleRepository = getRepository(this.application.applicationContext)
        }
    }else{
        frag = fragment
    }

    supportFragmentManager.beginTransaction().apply {
        //3) on remplace le contenu du container
        replace(R.id.fragment_container, frag)
        //4) on ajoute la transaction dans la backstack
        addToBackStack(null)
    }.commit()
       // 5) on commit la transaction
}
