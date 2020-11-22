package com.mbds.newsletter.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mbds.newsletter.R
import com.mbds.newsletter.data.article.ArticleRepository
import com.mbds.newsletter.model.Article
import com.mbds.newsletter.model.ArticleEntity
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers



class ArticleAdapter ( private val dataset: MutableList<Article>, val clickListener : (Article) -> Unit, val articleRepository: ArticleRepository, val displayFavorite : String = "") : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    val favoriteArticle = mutableListOf<ArticleEntity>()
    class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: Article,  postion: Int,  articleRepository: ArticleRepository, displayFavorite: String, clickListener: (Article) -> Unit) {

            val txtTitle = root.findViewById<TextView>(R.id.article_title)
            val imageView = root.findViewById<ImageView>(R.id.article_image)
            val articleSource = root.findViewById<TextView>(R.id.article_source)
            val articleDesc = root.findViewById<TextView>(R.id.article_description)

            val favoriteIcon = root.findViewById<ImageView>(R.id.favorite_icon)

           // addPaddingAndBackground(articleDesc, item.description)


            txtTitle.text = item.title
            articleSource.text = item.source
            articleDesc.text = item.description
            Log.d("favorite_view_holder", "${item.favoriteStatus}")
            favoriteIcon.setImageResource(R.drawable.ic_favorite_empty)
            //if article in favoriteArticle
            when(item.favoriteStatus){
                1 ->  Glide.with(root).load(R.drawable.ic_favorite_add).into(favoriteIcon)
                0 ->  Glide.with(root).load(R.drawable.ic_favorite_empty).into(favoriteIcon)
                else -> {
                    Glide.with(root).load(R.drawable.ic_favorite_empty).into(favoriteIcon)
                }
            }
            if(displayFavorite.isEmpty()){
                favoriteIcon.setOnClickListener {
                    /*Toast.makeText(
                            root.context,
                            item.title,
                            Toast.LENGTH_LONG
                    ).show()*/
                    when(item.favoriteStatus){
                        0 -> {
                            item.favoriteStatus = 1
                            CoroutineScope(Dispatchers.IO).launch {
                                articleRepository.updateArticle(item)
                                withContext(Dispatchers.Main) {
                                    Glide.with(root).load(R.drawable.ic_favorite_add).into(favoriteIcon)
                                }
                            }
                        }
                        1 -> {
                            item.favoriteStatus = 0
                            CoroutineScope(Dispatchers.IO).launch {
                                articleRepository.updateArticle(item)
                                withContext(Dispatchers.Main) {
                                    Glide.with(root).load(R.drawable.ic_favorite_empty).into(favoriteIcon)
                                }
                            }
                        }
                        else -> {

                        }
                    }
                }

            }


            Glide
                .with(root)
                .load(item.urlToImage)
                .fitCenter()
                .placeholder(R.drawable.placeholder)
                .into(imageView)


            root.setOnClickListener {
                clickListener(item)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_item, parent, false)
        return ViewHolder(rootView)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i("Fragment Dataset size","${dataset.size}")

        //get current Article
        val currentArticleEntity = dataset[position]
        CoroutineScope(Dispatchers.IO).launch{
            var articleEntity = articleRepository.getArticlesByUrl(currentArticleEntity.url)
            val favoriteStatus = articleEntity.favoriteStatus
            currentArticleEntity.favoriteStatus = favoriteStatus
            Log.d("favorite_db_status", "$favoriteStatus")
            withContext(Dispatchers.Main){
                when(favoriteStatus){
                    1 ->  Glide.with(holder.root).load(R.drawable.ic_favorite_add).into(holder.root.findViewById<ImageView>(R.id.favorite_icon))
                    0 ->  Glide.with(holder.root).load(R.drawable.ic_favorite_empty).into(holder.root.findViewById<ImageView>(R.id.favorite_icon))
                    else -> {
                        Glide.with(holder.root).load(R.drawable.ic_favorite_empty).into(holder.root.findViewById<ImageView>(R.id.favorite_icon))
                    }
                }
                holder.bind(currentArticleEntity, position, articleRepository, displayFavorite, clickListener)
            }
        }


    }

    /*fun addNextResults(itemsNew: List<ArticleEntity>){
        dataset.addAll(itemsNew)
        notifyDataSetChanged()
    }*/


       // holder.bind(dataset[position], clickListener)
    }

}