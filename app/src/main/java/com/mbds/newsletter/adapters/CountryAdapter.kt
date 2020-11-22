package com.mbds.newsletter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mbds.newsletter.R
import com.mbds.newsletter.model.Country
import com.mbds.newsletter.model.CountryDto
import com.mbds.newsletter.model.Source

class CountryAdapter (private val dataset: List<CountryDto>, val clickListener : (CountryDto) -> Unit) : RecyclerView.Adapter<CountryAdapter.ViewHolder>(){

    class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: CountryDto, clickListener: (CountryDto) -> Unit) {
            val txtTitle = root.findViewById<TextView>(R.id.country_name)
            val imageView = root.findViewById<ImageView>(R.id.country_flag)
            txtTitle.text =  item.countryName

            Glide
                 .with(root)
                 .load(item.url)
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
            .inflate(R.layout.list_countries, parent, false)
        return ViewHolder(rootView)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataset[position], clickListener)
    }
}