package com.example.myreditt.dataBase.store.list;

import com.example.myreditt.models.domain.RedditContent;

import java.util.List;

import rx.Observable;

/**
 * Created by Sanoop
 */

public interface ListPostsStoreDataSource {

    Observable<List<RedditContent>> getPosts(String subreddit);

    Observable<List<RedditContent>> getPaginatedPosts(String subreddit);

}
