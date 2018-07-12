package com.example.myreditt.singlePost

import com.example.myreditt.base.BasePresenter
import com.example.myreditt.base.BaseView
import com.example.myreditt.models.domain.Comment


/**
 * @author Sanoop.
 */

interface PostContractor {

    interface View : BaseView<Presenter> {

        fun showInfo()
        fun showComment(comment: Comment)

    }

    interface Presenter : BasePresenter {

        fun loadInfo(permalink: String)

    }

}
