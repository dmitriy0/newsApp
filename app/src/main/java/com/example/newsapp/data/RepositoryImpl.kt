package com.example.newsapp.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.domain.NewsItem
import com.example.newsapp.domain.NewsResponse
import com.example.newsapp.domain.Repository
import kotlinx.coroutines.internal.synchronized
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object RepositoryImpl : Repository {

    private const val API_KEY = "f10aabd510984d7591d01605a99fbc5f"

    override fun getNewsByTag(tag: String): List<NewsItem> {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: ApiService = retrofit.create(ApiService::class.java)
        val callNews = service.getAllNewsByTag(tag, API_KEY)
        return reformatDateInList(callNews.execute()?.body()?.articles)
    }

    private fun reformatDateInList(listNews: List<NewsItem>?): List<NewsItem> {

        val newList = mutableListOf<NewsItem>()
        listNews?.forEach {
            val newDate = "${reformatDate(it.publishedAt)} ${getTimeFromString(it.publishedAt)}"
            newList.add(it.copy(publishedAt = newDate))
        }

        return newList
    }

    private fun reformatDate(date: String): String {
        val currentDate = Calendar.getInstance().time
        val publishedDate = getDateFromString(date)

        val diff = currentDate.time - publishedDate.time
        val diffInDays = TimeUnit.MILLISECONDS.toDays(diff).toString()
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)

        return when(diffInDays) {
            "0" -> "Сегодня"
            "1" -> "Вчера"
            else -> formatter.format(publishedDate)
        }
    }

    private fun getDateFromString(stringDate: String): Date {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        return formatter.parse(stringDate.split("T")[0]) as Date
    }

    private fun getTimeFromString(stringDate: String): String {
        val formatter = SimpleDateFormat("HH:mm", Locale.US)
        val time = formatter.parse(stringDate.split("T")[1]) as Date
        return formatter.format(time)
    }

}