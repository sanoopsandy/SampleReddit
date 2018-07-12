package com.example.myreditt.home


import com.example.myreditt.adapter.base.DisplayableItem
import com.example.myreditt.base.BasePresenter
import com.example.myreditt.base.BaseView

/**
 * Created by Sanoop
 */

interface HomeContractor {

    interface View : BaseView<Presenter> {

        fun addedItem()

        fun modifiedItem(position: Int)

        fun enableScrollListener()

        fun initView(displayableItems: List<DisplayableItem>)

        fun showPost(permalink: String)
    }

    interface Presenter : BasePresenter {

        abstract fun loadMore()

        abstract fun onPostClicked(position: Int)

        abstract fun onFavoriteClicked(position: Int)
    }

}
