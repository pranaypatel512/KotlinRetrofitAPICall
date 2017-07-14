package com.pranay.kotlinretrofitapicall.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.pranay.kotlinretrofitapicall.R
import com.pranay.kotlinretrofitapicall.api.ApiProduction
import com.pranay.kotlinretrofitapicall.api.response.NewsListResponse
import com.pranay.kotlinretrofitapicall.api.service.NewsService
import com.pranay.kotlinretrofitapicall.rx.RxAPICallHelper
import com.pranay.kotlinretrofitapicall.rx.RxAPICallback
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

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
        var apiCall: Observable<NewsListResponse> = mNewsService.getNewsApi("the-verge", "top",
                "3c08e0a94cfe43e69f0386f05eb3177f" //Test API Key
        )
        var disposable: Disposable = RxAPICallHelper().call(apiCall, object : RxAPICallback<NewsListResponse> {
            override fun onSuccess(t: NewsListResponse) {
                //status= "error" in case of error
                if (t.getStatus().equals("ok")) {
                    Log.e("Image",t.getArticles()?.get(0)?.urlToImage)
                }
            }

            override fun onFailed(throwable: Throwable) {

            }
        })

    }

}
