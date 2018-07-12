package com.example.myreditt.singlePost

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.example.myreditt.R
import com.example.myreditt.adapter.ExpandableCommentGroup
import com.example.myreditt.base.BaseActivityWithPresenter
import com.example.myreditt.core.di.DIHandler
import com.example.myreditt.core.networking.PostService
import com.example.myreditt.dataBase.store.post.PostStore
import com.example.myreditt.models.domain.Comment
import com.example.myreditt.utils.visibilityToggle
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_single_post.*
import javax.inject.Inject

class PostActivity : BaseActivityWithPresenter<PostContractor.Presenter>(), PostContractor.View {

    @Inject
    lateinit var postService: PostService

    private val groupAdapter = GroupAdapter<ViewHolder>()
    private lateinit var groupLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_post)
        DIHandler.getHomeComponent().inject(this)
        setupViews()
        val postStore = PostStore(postService)
        setPresenter(PostPresenter(this, postStore))
        populateAdapter()

        groupLayoutManager = GridLayoutManager(this, groupAdapter.spanCount).apply {
            spanSizeLookup = groupAdapter.spanSizeLookup
        }

        rvComments.apply {
            layoutManager = groupLayoutManager
            adapter = groupAdapter
        }

    }

    private fun populateAdapter() {
        initData()
    }

    override fun showComment(comment: Comment) {
        groupAdapter.add(ExpandableCommentGroup(comment))
    }

    private fun initData() {
        val permalink = intent.getStringExtra(POST_PERMALINK)
        progress.visibilityToggle(true)
        presenter.loadInfo(permalink)
    }

    private fun setupViews() {
        setToolbar()
    }

    override fun showInfo() {
        progress.visibilityToggle(false)
    }

    companion object {
        val POST_PERMALINK = "_post_permalink"
    }
}
