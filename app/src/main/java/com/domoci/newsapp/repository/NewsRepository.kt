package com.domoci.newsapp.repository

import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import com.domoci.newsapp.api.RetrofitInstance
import com.domoci.newsapp.db.ArticleDatabase
import com.domoci.newsapp.models.Article


class NewsRepository(val db:ArticleDatabase) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    suspend fun upsert(article: Article): Unit {
        var mDbHt = HandlerThread("db");
        mDbHt.start(); // 初始化数据库的专用操作线程
        var mDbHandler =  Handler(mDbHt.looper);
        mDbHandler.post(Runnable {
            var id = db.getArticleDao().upsert(article)
        })
        return Unit
    }


    fun getSavedNews() = db.getArticleDao().getAllArticles()

     fun deleteArticle(article:Article): Unit {
        var mDbHt = HandlerThread("db");
        mDbHt.start(); // 初始化数据库的专用操作线程
        var mDbHandler =  Handler(mDbHt.looper);
        mDbHandler.post(Runnable {
            db.getArticleDao().deleteArticle(article)
        })
         return Unit
    }
}