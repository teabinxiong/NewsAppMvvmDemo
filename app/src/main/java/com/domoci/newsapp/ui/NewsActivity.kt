package com.domoci.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.domoci.newsapp.R
import com.domoci.newsapp.databinding.ActivityNewsBinding
import com.domoci.newsapp.db.ArticleDatabase
import com.domoci.newsapp.repository.NewsRepository

class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(application, newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)

        binding = ActivityNewsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment= supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(navHostFragment.findNavController())
    }
}