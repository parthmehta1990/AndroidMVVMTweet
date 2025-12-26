package com.example.tweets.api

import com.example.tweets.models.TweetListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetAPI {

    @GET("v3/b/694d6f14ae596e708fb0803d?meta=false")
    suspend fun getTweet(@Header("x-JSON-Path") category: String) : Response <List<TweetListItem>>

    @GET("v3/b/694d6f14ae596e708fb0803d?meta=false")
    @Headers("X-JSON-Path : tweets..category") // for static headers value
    suspend fun getCategory(): Response<List<String>>
}