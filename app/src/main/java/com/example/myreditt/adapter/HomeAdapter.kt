package com.example.myreditt.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

import com.example.myreditt.adapter.base.AdapterDelegateManager
import com.example.myreditt.adapter.base.DisplayableItem
import com.example.myreditt.adapter.delegates.RedditBigPostDelegate
import com.example.myreditt.adapter.delegates.RedditPostAdapterDelegate

/**
 * Created by Sanoop
 */

class HomeAdapter(private val items: List<DisplayableItem>, onPostClickListener: OnPostClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_REDDIT_POST = 1
    private val TYPE_BIG_REDDIT_POST = 2

    private val adapterDelegateManager: AdapterDelegateManager<List<DisplayableItem>>

    init {
        adapterDelegateManager = AdapterDelegateManager()
        adapterDelegateManager.addDelegate(RedditBigPostDelegate(TYPE_BIG_REDDIT_POST, onPostClickListener))
        adapterDelegateManager.addDelegate(RedditPostAdapterDelegate(TYPE_REDDIT_POST, onPostClickListener))
    }

    override fun getItemViewType(position: Int): Int {
        return adapterDelegateManager.getItemViewType(items, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return adapterDelegateManager.onCreateViewHolder(parent, viewType)!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        adapterDelegateManager.onBindViewHolder(items, holder, position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface OnPostClickListener {

        fun onPostClicked(position: Int)

    }

}
