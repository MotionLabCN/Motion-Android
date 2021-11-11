package com.newtouch.motion.ui.login

import com.common.baselibrary.base.IBasePresenter
import com.common.baselibrary.base.IBaseView

interface LoginContract {
    interface View : IBaseView {
        fun loginSuccess()
    }

    interface Presenter<T> : IBasePresenter<View> {
        fun login(username: String, password: String)
        fun getCode(phone:String)

        fun toLogin(phone:String,code:String)
    }

}