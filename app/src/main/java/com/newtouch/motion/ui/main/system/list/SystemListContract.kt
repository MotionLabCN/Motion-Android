package com.newtouch.motion.ui.main.system.list

import com.common.baselibrary.base.IBasePresenter
import com.common.baselibrary.base.IBaseView
import com.newtouch.motion.entity.SystemListEntity


interface SystemListContract {

    interface View:IBaseView{
        fun showList(list: MutableList<SystemListEntity>)
    }

    interface Presenter<T>:IBasePresenter<View>{
        fun loadData()
    }
}