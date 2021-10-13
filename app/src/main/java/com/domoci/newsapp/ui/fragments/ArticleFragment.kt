package com.domoci.newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.domoci.newsapp.R
import com.domoci.newsapp.databinding.FragmentArticleBinding
import com.domoci.newsapp.databinding.FragmentSearchNewsBinding
import com.domoci.newsapp.ui.NewsActivity
import com.domoci.newsapp.ui.NewsViewModel
import com.google.android.material.snackbar.Snackbar

class ArticleFragment : Fragment(R.layout.fragment_article) {
    lateinit var viewModel: NewsViewModel
    val args: ArticleFragmentArgs by navArgs()
    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        val article = args.article

        (article.url).let {
            binding.webView.apply {
                webViewClient = WebViewClient()
                loadUrl(it?:"")
            }

            binding.fab.setOnClickListener {
                viewModel.saveArticle(article)
                Snackbar.make(view, "Article saved successfully", Snackbar.LENGTH_SHORT).show()
            }
        } ?: run {

            findNavController().popBackStack()

        }

    }
}