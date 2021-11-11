package com.newtouch.motion.ui.set

import com.common.baselibrary.base.IBasePresenter
import com.common.baselibrary.base.IBaseView

interface SetContract {
    interface View:IBaseView{
        fun logoutSuccess()
    }

    interface Presenter<T>:IBasePresenter<View>{
        fun logout()
    }
}