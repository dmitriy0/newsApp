package com.example.newsapp.domain

import androidx.lifecycle.MutableLiveData

interface Repository {
    fun getNewsByTag(tag: String): List<NewsItem>
}