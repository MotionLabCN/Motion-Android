package com.newtouch.motion.ui.main.search.fragment

import com.common.baselibrary.base.IBasePresenter
import com.common.baselibrary.base.IBaseView
import com.newtouch.motion.entity.HotCodeListEntity
import com.newtouch.motion.entity.OpenCodeCategoryEntity
import com.newtouch.motion.entity.SystemListEntity


interface OpenSourceListContract {

    interface View:IBaseView{
        fun showHotCodeList(data: MutableList<OpenCodeCategoryEntity>)
        fun showHotCodeList(data: HotCodeListEntity)
        fun showNewStartList(data: HotCodeListEntity)

    }

    interface Presenter<T>:IBasePresenter<View>{

        fun getCategoryList()
        fun getHotCodeList(category: String,pageNum:Int)
        fun getNewStartList(category: String,pageNum:Int)


    }
}