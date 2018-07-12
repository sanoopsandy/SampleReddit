package com.example.myreditt.core.networking

import com.example.myreditt.models.domain.RedditResponse
import rx.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostService {
    @GET("/r/{subreddit}/.json")
    fun getSubreddit(@Path("subreddit") subreddit: String, @Query("after") after: String): Observable<RedditResponse>

    @GET("{permalink}.json")
    fun getPost(@Path(value = "permalink", encoded = true) permalink: String): Observable<List<RedditResponse>>
}