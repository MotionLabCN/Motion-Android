package com.newtouch.motion.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.donkingliang.labels.LabelsView
import com.newtouch.motion.R
import com.newtouch.motion.entity.NavigationEntity


/**
 * 导航 适配器
 */
class NavigationAdapter (layoutId:Int) : BaseQuickAdapter<NavigationEntity, BaseViewHolder>(layoutId){

    private var systemClickListener:OnSystemClickListener? = null
    fun setOnSystemClickListener(systemClickListener:OnSystemClickListener?){
        this.systemClickListener = systemClickListener
    }
    override fun convert(helper: BaseViewHolder, item: NavigationEntity?) {
        item?.let {
            helper.setText(R.id.tvTitle,item.name)
            helper.getView<LabelsView>(R.id.labelsView).apply {
                setLabels(item.articles) { _, _, data ->
                    data.title
                }
                //标签的点击监听
                setOnLabelClickListener { _, _, position ->
                    systemClickListener?.onCollectClick(helper,helper.adapterPosition,position)
                }
            }
        }
    }

}