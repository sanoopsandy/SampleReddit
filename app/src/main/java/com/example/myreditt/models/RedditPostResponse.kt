package com.example.myreditt.models

import com.google.gson.annotations.SerializedName

data class RedditPostResponse(@SerializedName("kind") var kind: String,
                              @SerializedName("data") var data: RedditPostData)


data class RedditPostData(@SerializedName("children") var children: List<PostChild>)

data class PostChild(@SerializedName("data") var childData: List<PostChildData>)

data class PostChildData(@SerializedName("subreddit") var subreddit: String,
                         @SerializedName("title") var title: String,
                         @SerializedName("thumbnail") var thumbnail: String,
                         @SerializedName("author") var author: String,
                         @SerializedName("num_comments") var numComments: Int,
                         @SerializedName("score") var score: Int)

