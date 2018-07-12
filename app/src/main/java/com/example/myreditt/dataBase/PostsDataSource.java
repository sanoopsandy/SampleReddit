package com.example.myreditt.dataBase;


import com.example.myreditt.models.domain.RedditContent;

import rx.Observable;

/**
 * Created by Sanoop
 */

public interface PostsDataSource {

    Observable<RedditContent> getPosts();

    Observable<RedditContent> getPaginatedPosts();

    void setFavorite(RedditContent redditContent, boolean favorite);

}
