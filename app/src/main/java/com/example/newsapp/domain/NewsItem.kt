package com.example.newsapp.domain

data class NewsItem(
    val author: String?,
    val title: String,
    val content: String,
    val urlToImage: String?,
    val publishedAt: String,
)
