package com.example.newsapp.domain

import androidx.lifecycle.MutableLiveData

class GetAllNewsByTagUseCase(private val repository: Repository) {
    fun getAllNewsByTag(tag: String): List<NewsItem> {
        return repository.getNewsByTag(tag)
    }
}