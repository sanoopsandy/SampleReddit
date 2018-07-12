package com.example.myreditt.singlePost

import android.util.Log
import com.example.myreditt.dataBase.CommentParser
import com.example.myreditt.dataBase.RedditDataParser
import com.example.myreditt.dataBase.store.post.PostStore
import com.example.myreditt.models.domain.Comment
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

/**
 * @author Sanoop.
 */

class PostPresenter(internal var view: PostContractor.View,
                    internal var postStore: PostStore) : PostContractor.Presenter {

    val compositeSubscription = CompositeSubscription()

    override fun start() {

    }

    override fun unsubscribe() {
        compositeSubscription.clear()
    }

    override fun loadInfo(permalink: String) {
        compositeSubscription.add(
                postStore.getPost(permalink)
                        .subscribeOn(Schedulers.io())
                        .flatMapIterable { redditResponses -> redditResponses }
                        .flatMap(RedditDataParser())
                        .filter { redditContent -> redditContent.kind == "t1" && redditContent.redditContentData != null }
                        .map(CommentParser())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : Observer<Comment> {
                            override fun onCompleted() {
                                Log.e("tag", "completed")
                                view.showInfo()
                            }

                            override fun onError(e: Throwable) {
                                Log.e("tag", "e " + e.printStackTrace())
                                view.showInfo()
                            }

                            override fun onNext(comment: Comment) {
                                view.showComment(comment)
                            }
                        }))
    }
}
