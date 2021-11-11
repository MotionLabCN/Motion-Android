package com.newtouch.motion.base

import android.content.Intent
import android.os.Bundle
import com.common.baselibrary.base.BaseActivity
import com.common.baselibrary.base.IBasePresenter
import com.newtouch.motion.ui.login.LoginActivity
import com.newtouch.motion.utils.AppManager

/**
 * des 主工程
 */
abstract class AppBaseActivity<P: IBasePresenter<*>>:BaseActivity<P>() {
    /**
     * 界面跳转
     * @param isLogin 启动界面是否需要登录
     */
    protected fun intent(clazz:Class<*>,isLogin:Boolean){
        //需要登录&&未登录
        if (isLogin && !AppManager.isLogin()) {
            startActivity(Intent(this, LoginActivity::class.java))
        }else{
            startActivity(Intent(this,clazz))
        }
    }
    /**
     * 携带bundle跳转
     * @param isLogin 启动界面是否需要登录
     */
    protected fun intent(bundle: Bundle, clazz:Class<*>, isLogin:Boolean){
        //需要登录&&未登录
        if (isLogin && !AppManager.isLogin()) {
            startActivity(Intent(this, LoginActivity::class.java))
        }else{
            startActivity(Intent(this, clazz).apply {
                putExtras(bundle)
            })
        }
    }
}