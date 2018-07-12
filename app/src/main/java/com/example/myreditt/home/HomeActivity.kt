package com.example.myreditt.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.myreditt.R
import com.example.myreditt.adapter.HomeAdapter
import com.example.myreditt.adapter.base.DisplayableItem
import com.example.myreditt.base.BaseActivityWithPresenter
import com.example.myreditt.core.di.DIHandler
import com.example.myreditt.core.networking.PostService
import com.example.myreditt.dataBase.store.list.ListPostsStoreManager
import com.example.myreditt.dataBase.store.list.ListsPostStoreList
import com.example.myreditt.singlePost.PostActivity
import com.example.myreditt.utils.HorizontalDividerDecoration
import com.example.myreditt.utils.LoadMoreRecyclerViewListener
import com.example.myreditt.utils.visibilityToggle
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class HomeActivity : BaseActivityWithPresenter<HomeContractor.Presenter>(), HomeContractor.View, HomeAdapter.OnPostClickListener {

    @Inject
    lateinit var postService: PostService

    private lateinit var homeAdapter: HomeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DIHandler.getHomeComponent().inject(this)
        val remoteStore = ListsPostStoreList(postService)
        val postStoreManager = ListPostsStoreManager(remoteStore)
        setPresenter(HomePresenter(this, postStoreManager, true))
        setToolbar()
        progressBar.visibilityToggle(true)
        presenter.start()
    }

    override fun onPostClicked(position: Int) {
        presenter.onPostClicked(position)
    }

    override fun addedItem() {
        progressBar.visibilityToggle(false)
        homeAdapter.notifyDataSetChanged()
    }

    override fun modifiedItem(position: Int) {
        homeAdapter.notifyItemChanged(position)
    }

    override fun enableScrollListener() {
        rvMain.addOnScrollListener(LoadMoreRecyclerViewListener(rvMain.layoutManager as LinearLayoutManager) { presenter.loadMore() })
    }

    override fun showError(error: String?) {
        super.showError(error)
        progressBar.visibilityToggle(false)
    }

    override fun initView(displayableItems: List<DisplayableItem>) {
        homeAdapter = HomeAdapter(displayableItems, this)
        rvMain.addItemDecoration(HorizontalDividerDecoration(this))
        rvMain.adapter = homeAdapter
        val linearLayoutManager = LinearLayoutManager(this)
        rvMain.layoutManager = linearLayoutManager
    }

    override fun showPost(permalink: String) {
        val intent = Intent(this, PostActivity::class.java)
        intent.putExtra(PostActivity.POST_PERMALINK, permalink)
        startActivity(intent)
    }
}
