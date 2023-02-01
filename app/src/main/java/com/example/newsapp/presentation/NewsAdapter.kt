package com.example.newsapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.domain.NewsItem
import com.squareup.picasso.Picasso

class NewsAdapter(var data: List<NewsItem>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    var newsItemClick: ((newsItem: NewsItem) -> Unit)? = null
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivImage: ImageView = view.findViewById(R.id.image)
        val tvTitle: TextView = view.findViewById(R.id.title)
        val tvDate: TextView = view.findViewById(R.id.date)
        val item: CardView = view.findViewById(R.id.item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsItem = data[position]
        Picasso.get().load(newsItem.urlToImage).into(holder.ivImage)
        holder.tvTitle.text = newsItem.title
        holder.tvDate.text = newsItem.publishedAt
        holder.item.setOnClickListener {
            newsItemClick?.invoke(newsItem)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}