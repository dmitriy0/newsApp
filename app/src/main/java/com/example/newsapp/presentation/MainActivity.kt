package com.example.newsapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myViewModel = ViewModelProvider(this)[MyViewModel::class.java]

        val etSearch = findViewById<EditText>(R.id.editTextSearch)
        val buttonSearch = findViewById<Button>(R.id.searchButton)

        recyclerView = findViewById(R.id.recyclerView)

        myViewModel.newsLiveData.observe(this) {
            newsAdapter = NewsAdapter(it)
            recyclerView.adapter = newsAdapter
        }

        buttonSearch.setOnClickListener {
            myViewModel.getAllNewsByTag(etSearch.text.toString())
        }

    }

    fun setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        newsAdapter = NewsAdapter(emptyList())
        recyclerView.adapter = newsAdapter
        newsAdapter.newsItemClick = {

        }
    }
}