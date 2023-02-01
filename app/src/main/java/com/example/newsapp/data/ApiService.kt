package com.example.newsapp.data

import com.example.newsapp.domain.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("everything/")
    fun getAllNewsByTag(
        @Query("q") tag: String,
        @Query("apiKey") apiKey: String,
        //@Query("country") country: String
    ): Call<NewsResponse>
}