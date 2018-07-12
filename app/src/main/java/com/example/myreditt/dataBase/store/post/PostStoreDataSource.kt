package com.example.myreditt.dataBase.store.post


import com.example.myreditt.models.domain.RedditResponse

import rx.Observable

/**
 * @author Sanoop
 */

interface PostStoreDataSource {

    fun getPost(permalink: String): Observable<List<RedditResponse>>

}
