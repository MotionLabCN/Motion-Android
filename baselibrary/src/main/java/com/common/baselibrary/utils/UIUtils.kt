package com.common.baselibrary.utils

import android.content.Context
import com.common.baselibrary.BaseApplication

object UIUtils {

    val context: Context get() = BaseApplication.getContext()

    fun dip2px(context: Context, dpValue: Float): Int {
        val density = context.resources.displayMetrics.density
        return (dpValue * density + 0.5).toInt()
    }

    fun getScreenWidth(context: Context): Int {
        return context.resources.displayMetrics.widthPixels
    }
}
