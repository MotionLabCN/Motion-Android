package com.common.baselibrary.base
import android.content.Context


interface IBaseView {

    fun getContext():Context?
    fun onError(error:String)
}