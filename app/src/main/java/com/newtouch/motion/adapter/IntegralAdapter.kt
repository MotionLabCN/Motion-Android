package com.newtouch.motion.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.newtouch.motion.R
import com.newtouch.motion.entity.IntegralRecordEntity


class IntegralAdapter(layoutId:Int) : BaseQuickAdapter<IntegralRecordEntity.DatasBean, BaseViewHolder>(layoutId) {

    override fun convert(helper: BaseViewHolder, item: IntegralRecordEntity.DatasBean?) {

        item?.apply {
            val desc = desc
            val firstSpace = desc?.indexOf(" ")
            val secondSpace = firstSpace?.plus(1)?.let { desc.indexOf(" ", it) }
            val time = secondSpace?.let { desc.substring(0, it) }
            val title = secondSpace?.plus(1)?.let {
                desc.substring(it)
                    .replace(",", "")
                    .replace("：", "")
                    .replace(" ", "")
            }
            helper.setText(R.id.tvAddIntegralMode,title)
            helper.setText(R.id.tvDate,time)
            helper.setText(R.id.tvAddIntegral,"+$coinCount")
        }
    }

}