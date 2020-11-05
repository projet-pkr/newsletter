package com.mbds.newsletter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mbds.newsletter.R
import com.mbds.newsletter.model.Category

class CategoryAdapter (private val dataset: List<Category>, val clickListener : (Category) -> Unit) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){

    class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: Category, clickListener: (Category) -> Unit) {
            val txtTitle = root.findViewById<TextView>(R.id.category_name)
            val imageView = root.findViewById<ImageView>(R.id.category_image)
            //val txtDesc = root.findViewById<TextView>(R.id.article_description)
            txtTitle.text = item.name
            //txtDesc.text = item.description
            Glide
                .with(root)
                .load(item.image)
                .fitCenter()
                .placeholder(R.drawable.placeholder)
                .into(imageView);

            root.setOnClickListener {
                clickListener(item)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_categories, parent, false)
        return ViewHolder(rootView)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataset[position], clickListener)
    }
}