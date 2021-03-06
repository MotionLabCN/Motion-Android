package com.newtouch.motion.ui.main.home

import com.common.baselibrary.base.IBasePresenter
import com.common.baselibrary.base.IBaseView
import com.newtouch.motion.entity.BannerEntity
import com.newtouch.motion.entity.ArticleEntity

interface HomeContract {
    interface View : IBaseView {
        fun showList(list: MutableList<ArticleEntity.DatasBean>)
        fun showBanner(bannerList:MutableList<BannerEntity>)
        fun collectSuccess()
        fun unCollectSuccess()
    }

    interface Presenter<T> : IBasePresenter<View> {
        fun loadData(pageNum:Int)
        fun loadBanner()
        fun collect(id:Int)
        fun unCollect(id:Int)
    }
}