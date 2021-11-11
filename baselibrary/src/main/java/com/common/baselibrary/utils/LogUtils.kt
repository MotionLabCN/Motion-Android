package com.common.baselibrary.utils

import android.util.Log


object LogUtils {

    fun d(string: String) {
        Log.d("app-log", string)
    }

    fun w(string: String) {
        Log.w("app-log", string)
    }

    fun e(string: String) {
        Log.e("app-log", string)
    }
}
