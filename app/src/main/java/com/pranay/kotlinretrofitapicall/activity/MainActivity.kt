package com.pranay.kotlinretrofitapicall.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.pranay.kotlinretrofitapicall.R
import com.pranay.kotlinretrofitapicall.api.ApiProduction
import com.pranay.kotlinretrofitapicall.api.response.NewsListResponse
import com.pranay.kotlinretrofitapicall.api.service.NewsService
import com.pranay.kotlinretrofitapicall.rx.RxAPICallHelper
import com.pranay.kotlinretrofitapicall.rx.RxAPICallback
import com.pranay.kotlinroomdbtodo.adapter.NewsRecyclerAdapter
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerNews: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        recyclerNews = findViewById(R.id.recyclerNews)
        getNewsList()
    }

    /**
     * Get News List from API
     */
    private fun getNewsList() {
        //Create retrofit Service
        var mNewsService: NewsService = ApiProduction(this).provideService(NewsService::class.java)
        //List of source : https://newsapi.org/sources
        //List of sort by option: https://newsapi.org/#apiArticles
        var apiCall: Observable<NewsListResponse> = mNewsService.getNewsApi("techcrunch", "top",
                getString(R.string.new_api_key) //Test API Key
        )
        RxAPICallHelper().call(apiCall, object : RxAPICallback<NewsListResponse> {
            override fun onSuccess(newsItems: NewsListResponse) {
                //status= "error" in case of error
                if (newsItems.getStatus().equals("ok")) {
                    Log.e("Image", newsItems.getArticles()?.get(0)?.urlToImage)
                    setNewsData(newsItems)
                }
            }

            override fun onFailed(throwable: Throwable) {

            }
        })

    }

    private fun setNewsData(newsItems: NewsListResponse) {
        recyclerNews.layoutManager = LinearLayoutManager(this)

        val newsRecyclerAdapter: NewsRecyclerAdapter = NewsRecyclerAdapter()
        newsRecyclerAdapter.setData(newsItems.getArticles() as ArrayList<NewsListResponse.Article>)
        recyclerNews.adapter = newsRecyclerAdapter
        newsRecyclerAdapter.setOnItemClick(object : NewsRecyclerAdapter.MyAdapterListener {
            override fun onItemViewClick(mUrl:String,position: Int) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(
                        mUrl)))
            }
        })
    }

}
