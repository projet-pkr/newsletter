package com.mbds.newsletter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mbds.newsletter.R
import com.mbds.newsletter.model.Source

class SourceAdapter (private val dataset: List<Source>, val clickListener : (Source) -> Unit) : RecyclerView.Adapter<SourceAdapter.ViewHolder>(){

    class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: Source, clickListener: (Source) -> Unit) {
            val txtTitle = root.findViewById<TextView>(R.id.category_name)
            val imageView = root.findViewById<ImageView>(R.id.category_image)
            txtTitle.text = item.name
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