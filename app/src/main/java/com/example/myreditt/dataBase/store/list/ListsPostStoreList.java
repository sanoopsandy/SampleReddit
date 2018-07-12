package com.example.myreditt.dataBase.store.list;

import com.example.myreditt.core.networking.PostService;
import com.example.myreditt.dataBase.store.RedditPostsRequest;
import com.example.myreditt.models.domain.RedditContent;
import com.example.myreditt.models.domain.RedditResponse;
import com.nytimes.android.external.store.base.Fetcher;
import com.nytimes.android.external.store.base.impl.RealStore;

import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;

import rx.Observable;
import rx.schedulers.Schedulers;


/**
 * Created by Sanoop
 */

public class ListsPostStoreList implements ListPostsStoreDataSource {

    private PostService postService;
    private String after;
    private Random random = new Random();
    private RealStore<List<RedditContent>, RedditPostsRequest> remoteStore;

    private Fetcher<List<RedditContent>, RedditPostsRequest> fetcher = new Fetcher<List<RedditContent>, RedditPostsRequest>() {
        @Nonnull
        @Override
        public Observable<List<RedditContent>> fetch(@Nonnull RedditPostsRequest request) {
            return getPosts(request.getKey(), request.getAfter());
        }
    };

    private Observable<List<RedditContent>> getPosts(String subreddit, String after) {
        return postService.getSubreddit(subreddit, after)
                .map(RedditResponse::getData)
                .subscribeOn(Schedulers.io())
                .flatMap(redditData -> {
                    ListsPostStoreList.this.after = redditData.getAfter();
                    return Observable.from(redditData.getChildren());
                })
                .filter(redditPostMetadata -> !redditPostMetadata.getRedditContentData().isNsfw())
                .map(redditPostMetadata -> {
                    int r = random.nextInt(10);
                    if (r % 5 == 0) {
                        redditPostMetadata.setBigPost(true);
                        return redditPostMetadata;
                    } else {
                        return redditPostMetadata;
                    }
                })
                .toList();
    }


    public ListsPostStoreList(PostService postService) {
        this.postService = postService;
        this.remoteStore = new RedditPostRealStore(fetcher);
    }

    @Override
    public Observable<List<RedditContent>> getPosts(String subreddit) {
        RedditPostsRequest redditPostsRequest = new RedditPostsRequest(subreddit, RedditContent.class.getSimpleName(), null);
        return remoteStore.get(redditPostsRequest);
    }

    @Override
    public Observable<List<RedditContent>> getPaginatedPosts(String subreddit) {
        RedditPostsRequest redditPostsRequest = new RedditPostsRequest(subreddit, RedditContent.class.getSimpleName(), after);
        return remoteStore.get(redditPostsRequest);
    }

}
