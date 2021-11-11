package com.common.baselibrary.base

import android.os.Bundle


/**
 * des 基于androidx实现懒加载
 */
abstract class LazyFragment<P: IBasePresenter<*>>: BaseFragment<P>() {

    private var isLoaded = false
    override fun onResume() {
        super.onResume()
        //增加了Fragment是否可见的判断
        if (!isLoaded && !isHidden) {
            lazyInit()
            isLoaded = true
        }
    }

    override fun init(savedInstanceState: Bundle?) {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded = false
    }

    abstract fun lazyInit()

}
