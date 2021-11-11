package com.newtouch.motion.base

import android.content.Intent
import android.os.Bundle
import com.common.baselibrary.base.IBasePresenter
import com.common.baselibrary.base.LazyFragment
import com.newtouch.motion.ui.login.LoginActivity
import com.newtouch.motion.utils.AppManager

/**
 * des 主工程懒加载BaseFragment
 */
abstract class AppLazyFragment<P: IBasePresenter<*>>:LazyFragment<P>() {
    /**
     * 界面跳转
     * @param isLogin 启动界面是否需要登录
     */
    protected fun intent(clazz:Class<*>, isLogin:Boolean){
        //需要登录&&未登录
        if (isLogin && !AppManager.isLogin()) {
            startActivity(Intent(context, LoginActivity::class.java))
        }else{
            startActivity(Intent(context,clazz))
        }
    }

    /**
     * 携带bundle跳转
     * @param isLogin 启动界面是否需要登录
     */
    protected fun intent(bundle: Bundle, clazz:Class<*>, isLogin:Boolean){
        //需要登录&&未登录
        if (isLogin && !AppManager.isLogin()) {
            startActivity(Intent(context, LoginActivity::class.java))
        }else{
            startActivity(Intent(context, clazz).apply {
                putExtras(bundle)
            })
        }
    }

}