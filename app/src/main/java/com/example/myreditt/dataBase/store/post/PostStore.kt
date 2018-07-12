package com.example.myreditt.dataBase.store.post

import com.example.myreditt.core.networking.PostService
import com.example.myreditt.models.domain.RedditResponse
import com.nytimes.android.external.store.base.Fetcher
import com.nytimes.android.external.store.base.impl.BarCode
import com.nytimes.android.external.store.base.impl.Store
import com.nytimes.android.external.store.base.impl.StoreBuilder

import rx.Observable

/**
 * @author Sanoop
 */

class PostStore(private val postService: PostService) : PostStoreDataSource {

    private val store: Store<List<RedditResponse>, BarCode>

    private val fetcher = Fetcher<List<RedditResponse>, BarCode> { barCode -> getRemotePost(barCode.key) }

    init {
        this.store = StoreBuilder.barcode<List<RedditResponse>>()
                .fetcher(fetcher)
                .open()
    }

    private fun getRemotePost(key: String): Observable<List<RedditResponse>> {
        return postService.getPost(key)
    }

    override fun getPost(permalink: String): Observable<List<RedditResponse>> {
        val barCode = BarCode("Post", permalink)
        return store.get(barCode)
    }

}
