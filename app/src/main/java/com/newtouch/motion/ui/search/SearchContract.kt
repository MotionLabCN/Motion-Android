package com.newtouch.motion.ui.search

import com.common.baselibrary.base.IBasePresenter
import com.common.baselibrary.base.IBaseView
import com.newtouch.motion.entity.ArticleEntity



interface SearchContract {
    interface View: IBaseView {
        fun showList(list: MutableList<ArticleEntity.DatasBean>)
        fun collectSuccess()
        fun unCollectSuccess()
    }

    interface Presenter<T> : IBasePresenter<View> {
        fun search(pageNum:Int,key:String)
        fun collect(id:Int)
        fun unCollect(id:Int)
    }
}