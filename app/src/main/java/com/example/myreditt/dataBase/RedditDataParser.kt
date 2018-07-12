package com.example.myreditt.dataBase

import com.example.myreditt.models.domain.RedditContent
import com.example.myreditt.models.domain.RedditData
import com.example.myreditt.models.domain.RedditResponse
import rx.Observable
import rx.functions.Func1

/**
 * @author Sanoop.
 */
class RedditDataParser : Func1<RedditResponse, Observable<RedditContent>> {

    private var after: String? = null

    override fun call(redditResponse: RedditResponse?): Observable<RedditContent> {
        return Observable
                .just(redditResponse?.data)
                .flatMap { redditData: RedditData? ->
                    this.after = redditData?.after
                    if (redditData == null) {
                        Observable.empty()
                    } else {
                        Observable.from(redditData.children)
                    }
                }

    }
}