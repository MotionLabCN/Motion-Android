package com.newtouch.motion.ui.main.tab

import com.common.baselibrary.base.IBasePresenter
import com.common.baselibrary.base.IBaseView
import com.newtouch.motion.entity.TabEntity


interface TabContract {
    interface View:IBaseView{
        fun showList(list:MutableList<TabEntity>)
    }

    interface Presenter<T>:IBasePresenter<View>{
        fun loadData(type:Int)
    }
}