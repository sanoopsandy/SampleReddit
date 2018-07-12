package com.example.myreditt.dataBase.store.list;


import com.example.myreditt.dataBase.store.RedditPostsRequest;
import com.example.myreditt.models.domain.RedditContent;
import com.nytimes.android.external.store.base.Fetcher;
import com.nytimes.android.external.store.base.impl.RealStore;

import java.util.List;

import rx.Observable;

/**
 * Created by Sanoop
 */

public class RedditPostRealStore extends RealStore<List<RedditContent>, RedditPostsRequest> {

    public RedditPostRealStore(Fetcher<List<RedditContent>, RedditPostsRequest> fetcher) {
        super(fetcher);
    }

    Observable<List<RedditContent>> getPosts(RedditPostsRequest request) {
        return get(request);
    }

}

