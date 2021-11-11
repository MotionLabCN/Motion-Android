package com.newtouch.motion.ui.main.system.activity

import com.common.baselibrary.base.IBasePresenter
import com.common.baselibrary.base.IBaseView
import com.newtouch.motion.entity.ArticleEntity


interface SystemContract {

    interface View: IBaseView {
        fun showList(list: MutableList<ArticleEntity.DatasBean>)
        fun collectSuccess()
        fun unCollectSuccess()
    }
    interface Presenter<T> : IBasePresenter<View> {
        fun loadData(pageNum:Int,cid:Int)
        fun collect(id:Int)
        fun unCollect(id:Int)
    }
}