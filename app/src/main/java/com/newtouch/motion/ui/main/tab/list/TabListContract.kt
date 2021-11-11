package com.newtouch.motion.ui.main.tab.list

import com.common.baselibrary.base.IBasePresenter
import com.common.baselibrary.base.IBaseView
import com.newtouch.motion.entity.ArticleEntity


interface TabListContract {
    interface View:IBaseView{
        fun showList(list:MutableList<ArticleEntity.DatasBean>)
        fun collectSuccess()
        fun unCollectSuccess()
    }
    interface Presenter<T>:IBasePresenter<View>{
        fun loadData(type:Int,id:Int,pageNum:Int)
        fun collect(id:Int)
        fun unCollect(id:Int)
    }
}