package com.example.newsapp.presentation

import com.example.newsapp.domain.Repository
import com.squareup.picasso.Picasso


@dagger.Component(modules = [RepositoryModule::class])

interface Component {
    val repositoryImpl: Repository
}