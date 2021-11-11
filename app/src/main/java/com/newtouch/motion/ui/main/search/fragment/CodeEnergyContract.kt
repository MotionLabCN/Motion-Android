package com.newtouch.motion.ui.main.search.fragment

import com.common.baselibrary.base.IBasePresenter
import com.common.baselibrary.base.IBaseView
import com.newtouch.motion.entity.CodeEnergyEntity
import com.newtouch.motion.entity.HotCodeListEntity
import com.newtouch.motion.entity.OpenCodeCategoryEntity
import com.newtouch.motion.entity.SystemListEntity


interface CodeEnergyContract {

    interface View:IBaseView{
        fun showCodeEnergyList(data: CodeEnergyEntity)

    }

    interface Presenter<T>:IBasePresenter<View>{
        fun getCodeEnergyList(category: String,pageNum:Int)

    }
}