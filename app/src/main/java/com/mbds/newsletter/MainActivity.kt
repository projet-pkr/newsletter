package com.mbds.newsletter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
            R.id.action_favoris -> {
                changeFragment(ArticlesFragment.newInstance(displayFavorite = "displayFavorite"))
                true
            }
            R.id.action_about -> true
            else ->{
                super.onOptionsItemSelected(item);
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
