package com.mbds.newsletter.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mbds.newsletter.R
import com.mbds.newsletter.data.article.ArticleRepository
import com.mbds.newsletter.factory.ArticleEntityFactory
import com.mbds.newsletter.model.Article
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers


class ArticleAdapter ( private val dataset: MutableList<Article>,  val articleRepository: ArticleRepository, val displayFavorite : String = "", val clickListener : (Article) -> Unit) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {


    class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: Article,  postion: Int,  articleRepository: ArticleRepository, favoriteStatus : Int , displayFavorite: String, clickListener: (Article) -> Unit) {

            val txtTitle = root.findViewById<TextView>(R.id.article_title)
            val imageView = root.findViewById<ImageView>(R.id.article_image)
            val articleSource = root.findViewById<TextView>(R.id.article_source)
            val articleDesc = root.findViewById<TextView>(R.id.article_description)

            val favoriteIcon = root.findViewById<ImageView>(R.id.favorite_icon)

            txtTitle.text = item.title
            articleSource.text = item.source.name
            articleDesc.text = item.description
            //Log.d("favorite_view_holder", "$favoriteStatus")
            favoriteIcon.setImageResource(R.drawable.ic_favorite_empty)

            //if article in favoriteArticle
            when(favoriteStatus){
                1 ->  Glide.with(root).load(R.drawable.ic_favorite_add).into(favoriteIcon)
                0 ->  Glide.with(root).load(R.drawable.ic_favorite_empty).into(favoriteIcon)
                else -> {
                    Glide.with(root).load(R.drawable.ic_favorite_empty).into(favoriteIcon)
                }
            }

            favoriteIcon.setOnClickListener {
                when(favoriteStatus){
                    0 -> {
                        CoroutineScope(Dispatchers.IO).launch {
                            articleRepository.updateArticle(ArticleEntityFactory.newInstance(item,1))
                            withContext(Dispatchers.Main) {
                                Glide.with(root).load(R.drawable.ic_favorite_add).into(favoriteIcon)
                            }
                        }
                    }
                    1 -> {

                        CoroutineScope(Dispatchers.IO).launch {
                            articleRepository.updateArticle(ArticleEntityFactory.newInstance(item,0))
                            withContext(Dispatchers.Main) {
                                Glide.with(root).load(R.drawable.ic_favorite_empty).into(favoriteIcon)
                            }
                        }
                    }
                    else -> {
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
        //Log.i("Fragment Dataset size","${dataset.size}")

        //get current Article
        val currentArticleEntity = ArticleEntityFactory.newInstance(dataset[position])
        CoroutineScope(Dispatchers.IO).launch{
            var articleEntity = articleRepository.getArticlesByUrl(currentArticleEntity.url)
            val favoriteStatus = articleEntity.favoriteStatus
            currentArticleEntity.favoriteStatus = favoriteStatus
            //Log.d("favorite_db_status", "$favoriteStatus")
            withContext(Dispatchers.Main){
                when(favoriteStatus){
                    1 ->  Glide.with(holder.root).load(R.drawable.ic_favorite_add).into(holder.root.findViewById<ImageView>(R.id.favorite_icon))
                    0 ->  Glide.with(holder.root).load(R.drawable.ic_favorite_empty).into(holder.root.findViewById<ImageView>(R.id.favorite_icon))
                    else -> {
                        Glide.with(holder.root).load(R.drawable.ic_favorite_empty).into(holder.root.findViewById<ImageView>(R.id.favorite_icon))
                    }
                }
                holder.bind(dataset[position], position, articleRepository, favoriteStatus,displayFavorite, clickListener)
            }
        }
    }
}