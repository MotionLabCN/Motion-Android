package com.newtouch.motion.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.newtouch.motion.R


class HotTopicsAdapter() : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_hot_topics) {


    override fun convert(helper: BaseViewHolder, item: String?) {

            item?.run {
//                if (type==1){
//                    helper.setText(R.id.tvTag,"置顶 ")
//                    helper.setTextColor(R.id.tvTag, ColorUtils.parseColor(R.color.red))
//                }else{
//                    helper.setText(R.id.tvTag,"")
//                }

            }


        }
}