package com.newtouch.motion.ui.Integral

import com.common.baselibrary.base.IBasePresenter
import com.common.baselibrary.base.IBaseView
import com.newtouch.motion.entity.IntegralRecordEntity


interface IntegralContract {

    interface View:IBaseView{
        fun showList(t: IntegralRecordEntity)

    }

    interface Presenter<T>:IBasePresenter<View>{
        fun loadData(pageNum:Int)

    }
}