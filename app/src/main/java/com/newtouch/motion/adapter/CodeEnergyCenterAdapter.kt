package com.newtouch.motion.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.newtouch.motion.R

/**
 * 文章适配器
 */
class CodeEnergyCenterAdapter() : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_code_energy_center) {

    private var collectClickListener:OnCollectClickListener? = null

    fun setCollectClickListener(collectClickListener:OnCollectClickListener){
        this.collectClickListener = collectClickListener
    }


    override fun convert(helper: BaseViewHolder, item: String?) {

        helper.apply {
            item.also {
                if (adapterPosition%2==0){
                    setGone(R.id.top_view,true)
                }else {
                    setGone(R.id.top_view,false)
                }
            }
        }


        }
}