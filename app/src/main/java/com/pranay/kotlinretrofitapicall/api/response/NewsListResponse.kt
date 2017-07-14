package com.pranay.kotlinretrofitapicall.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by Pranay on 7/15/2017.
 */
class NewsListResponse {
    @SerializedName("status")
    @Expose
    private var status: String? = null
    @SerializedName("source")
    @Expose
    private var source: String? = null
    @SerializedName("sortBy")
    @Expose
    private var sortBy: String? = null
    @SerializedName("articles")
    @Expose
    private var articles: List<Article>? = null

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String) {
        this.status = status
    }

    fun getSource(): String? {
        return source
    }

    fun setSource(source: String) {
        this.source = source
    }

    fun getSortBy(): String? {
        return sortBy
    }

    fun setSortBy(sortBy: String) {
        this.sortBy = sortBy
    }

    fun getArticles(): List<Article>? {
        return articles
    }

    fun setArticles(articles: List<Article>) {
        this.articles = articles
    }

    inner class Article {

        @SerializedName("author")
        @Expose
        var author: String? = null
        @SerializedName("title")
        @Expose
        var title: String? = null
        @SerializedName("description")
        @Expose
        var description: String? = null
        @SerializedName("url")
        @Expose
        var url: String? = null
        @SerializedName("urlToImage")
        @Expose
        var urlToImage: String? = null
        @SerializedName("publishedAt")
        @Expose
        var publishedAt: String? = null

    }
}