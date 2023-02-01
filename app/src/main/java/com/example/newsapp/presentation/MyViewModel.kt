package com.example.newsapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.RepositoryImpl
import com.example.newsapp.domain.GetAllNewsByTagUseCase
import com.example.newsapp.domain.NewsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    var newsLiveData = MutableLiveData<List<NewsItem>>()
    private val daggerComponent = DaggerComponent.builder().build()
    private val getAllNewsByTagUseCase = GetAllNewsByTagUseCase(daggerComponent.repositoryImpl)

    fun getAllNewsByTag(tag: String) {
        viewModelScope.launch(Dispatchers.IO) {
            newsLiveData.postValue(getAllNewsByTagUseCase.getAllNewsByTag(tag))
        }

    }
}