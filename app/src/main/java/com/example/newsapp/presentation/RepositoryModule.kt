package com.example.newsapp.presentation

import com.example.newsapp.data.RepositoryImpl
import com.example.newsapp.domain.Repository

import dagger.Provides


@dagger.Module
class RepositoryModule {

    @Provides
    fun repository(): Repository {
        return RepositoryImpl
    }

}