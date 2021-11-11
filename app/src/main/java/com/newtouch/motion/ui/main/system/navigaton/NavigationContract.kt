package com.newtouch.motion.ui.main.system.navigaton

import com.common.baselibrary.base.IBasePresenter
import com.common.baselibrary.base.IBaseView
import com.newtouch.motion.entity.NavigationEntity


/**
 * des 导航列表
 */
interface NavigationContract {
    interface View: IBaseView {
        fun showList(list: MutableList<NavigationEntity>)
    }

    interface Presenter<T>: IBasePresenter<View> {
        fun loadData()
    }
}