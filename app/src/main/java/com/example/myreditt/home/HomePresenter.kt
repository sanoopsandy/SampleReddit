package com.example.myreditt.home


import com.example.myreditt.adapter.base.DisplayableItem
import com.example.myreditt.dataBase.store.list.ListPostsStoreDataSource
import com.example.myreditt.models.domain.RedditContent
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import java.util.*

/**
 * Created by Sanoop
 */

class HomePresenter(private val view: HomeContractor.View, private val postsRepository: ListPostsStoreDataSource, private val isInternetAvailable: Boolean) : HomeContractor.Presenter {
    private var subscription: Subscription? = null
    private val displayableItemList: MutableList<DisplayableItem>

    init {
        displayableItemList = ArrayList()
    }

    override fun start() {
        view.initView(displayableItemList)
        getRedditPosts()
        if (isInternetAvailable) {
            view.enableScrollListener()
        }
    }

    override fun loadMore() {
        getPaginatedPosts()
    }

    override fun onPostClicked(position: Int) {
        val item = displayableItemList[position]
        val redditContent = item as RedditContent
        view.showPost(redditContent.redditContentData.permalink)
    }

    override fun onFavoriteClicked(position: Int) {
        val item = displayableItemList[position]
        val redditContent = item as RedditContent
        redditContent.isFavorite = !redditContent.isFavorite
        view.modifiedItem(position)
    }

    override fun unsubscribe() {
        if (subscription != null && !subscription!!.isUnsubscribed) {
            subscription!!.unsubscribe()
        }
    }

    private fun getRedditPosts() {
        subscription = postsRepository.getPosts("nintendoswitch")
                .flatMap<RedditContent> { Observable.from(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ redditPostMetadata ->
                    displayableItemList.add(redditPostMetadata)
                    view.addedItem()
                }, { e -> view.showError(e.localizedMessage) })
    }

    private fun getPaginatedPosts() {
        subscription = postsRepository.getPaginatedPosts("nintendoswitch")
                .flatMap<RedditContent> { Observable.from(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ redditPostMetadata ->
                    displayableItemList.add(redditPostMetadata)
                    view.addedItem()
                }, { e -> view.showError(e.localizedMessage) })
    }
}
