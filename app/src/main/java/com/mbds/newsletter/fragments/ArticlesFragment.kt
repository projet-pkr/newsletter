package com.mbds.newsletter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mbds.newsletter.MainActivity
import com.mbds.newsletter.R
import com.mbds.newsletter.adapters.ArticleAdapter
import com.mbds.newsletter.changeFragment
import com.mbds.newsletter.services.ArticleOnlineService
import com.mbds.newsletter.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * A simple [Fragment] subclass.
 * Use the [ArticlesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArticlesFragment : Fragment() {

    lateinit var articleOnlineService : ArticleOnlineService
    lateinit var sourceId: String
    lateinit var countryId : String
    lateinit var category : String
    lateinit var type : String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_articles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //get data
        lifecycleScope.launch{
            getData(view)
        }
    }

    private suspend fun getData(view: View){
        withContext(Dispatchers.IO){
            //val result = repository.list()

            val result = when(type){
                "1" -> articleOnlineService.getArticlesBySourceId(sourceId)
                "2" -> articleOnlineService.getArticlesByCategory(category)
                "3" -> articleOnlineService.getArticlesByCountry(countryId)
                else -> {
                   listOf<Article>()
                }
            }

            bindData(result, view)
        }
    }
    private suspend fun bindData(result : List<Article>?, view: View){
        withContext(Dispatchers.Main){
            //display data in the recycler
            val recyclerView: RecyclerView = view.findViewById(R.id.article_recycler_view)
            val listOfArticles = result ?: emptyList()
            val adapterRecycler = ArticleAdapter(listOfArticles.toMutableList()){
                itemClicked(it)
            }
            val gridLayoutManager = GridLayoutManager(view.context, 1)
            recyclerView.layoutManager = gridLayoutManager
            recyclerView.adapter = adapterRecycler
        }
    }

    private fun itemClicked(article: Article){
        Toast.makeText(
            context,
            article.title,
            Toast.LENGTH_LONG
        ).show()
        (activity as? MainActivity)?.changeFragment(
            AticleDetailsFragment.newInstance(article)
        )
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ArticlesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(sourceId : String = "", category: String = "", countryId  : String = "") =
            ArticlesFragment().apply {
                this.sourceId = sourceId
                this.category = category
                this.countryId = countryId
                this.articleOnlineService = ArticleOnlineService()
                this.type = mapOf(1 to sourceId, 2 to category, 3 to countryId).filter {
                    it.value.isNotEmpty()
                }.keys.first().toString()
            }
    }

}