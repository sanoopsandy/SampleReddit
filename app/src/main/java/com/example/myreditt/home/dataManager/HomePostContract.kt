package com.example.myreditt.home.dataManager

import com.example.myreditt.core.networking.DataResult
import com.example.myreditt.models.domain.RedditContent
import com.example.myreditt.models.domain.RedditResponse
import io.reactivex.Flowable
import io.reactivex.subjects.PublishSubject

interface HomePostContract {
    interface Repository {
        val postHomePostResult: PublishSubject<DataResult<List<RedditContent>>>
        fun fetchHomePost(subReddit: String)
        fun refreshPost(subReddit: String)
        fun savePosts(storyItem: List<RedditContent>)
        fun handleError(error: Throwable)
    }

    interface Remote {
        fun getPosts(subReddit: String): Flowable<RedditResponse>
    }

    interface Local {
        fun getHomePosts(subReddit: String): Flowable<List<RedditContent>>
        fun saveHomePosts(storyItem: List<RedditContent>)
    }
}