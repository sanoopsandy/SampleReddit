package com.example.myreditt.adapter.base

import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.ViewGroup

/**
 * Created by Sanoop
 */

class AdapterDelegateManager<T> {

    private val adapterDelegateList: SparseArray<AdapterDelegate<T>>

    init {
        adapterDelegateList = SparseArray()
    }

    fun addDelegate(adapterDelegate: AdapterDelegate<T>) {
        adapterDelegateList.put(adapterDelegate.viewType, adapterDelegate)
    }

    fun deleteDelegate(adapterDelegate: AdapterDelegate<T>) {
        if (adapterDelegateList.size() != 0) {
            adapterDelegateList.remove(adapterDelegate.viewType)
        }
    }

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        val adapterDelegate = getAdapterDelegateByViewType(viewType)
        return adapterDelegate?.onCreateViewHolder(parent)
    }

    fun onBindViewHolder(items: T, viewHolder: RecyclerView.ViewHolder, position: Int) {
        val adapterDelegate: AdapterDelegate<T> = getAdapterDelegateByViewType(viewHolder.itemViewType) as AdapterDelegate<T>
        adapterDelegate.onBindViewHolder(items, viewHolder, position)
    }

    private fun getAdapterDelegateByViewType(viewType: Int): AdapterDelegate<*>? {
        return if (adapterDelegateList.size() > 0) {
            adapterDelegateList.get(viewType)
        } else {
            null
        }
    }


    fun getItemViewType(items: T, position: Int): Int {
        if (adapterDelegateList.size() > 0) {
            for (i in 0 until adapterDelegateList.size()) {
                val adapterDelegate = adapterDelegateList.valueAt(i)
                if (adapterDelegate.isForViewType(items, position)) {
                    return adapterDelegate.viewType
                }
            }
            return -1
        } else {
            return -1
        }
    }

}
