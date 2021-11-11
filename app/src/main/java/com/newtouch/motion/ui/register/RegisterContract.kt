package com.newtouch.motion.ui.register

import com.common.baselibrary.base.IBasePresenter
import com.common.baselibrary.base.IBaseView


interface RegisterContract {

    interface View:IBaseView{
        fun registerSuccess()
    }

    interface Presenter<T>:IBasePresenter<View>{
        fun register(username:String,password:String,repassword:String)
    }

}