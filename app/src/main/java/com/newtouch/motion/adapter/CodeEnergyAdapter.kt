package com.newtouch.motion.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.newtouch.motion.R
import com.newtouch.motion.entity.CodeEnergyEntity
import com.newtouch.motion.proxy.ImageLoad


class CodeEnergyAdapter() : BaseQuickAdapter<CodeEnergyEntity.DataBean, BaseViewHolder>(R.layout.item_code_energy) {

    private var collectClickListener:OnCollectClickListener? = null

    fun setCollectClickListener(collectClickListener:OnCollectClickListener){
        this.collectClickListener = collectClickListener
    }


    override fun convert(helper: BaseViewHolder, item: CodeEnergyEntity.DataBean?) {

        item?.apply {
            ImageLoad.load(helper.getView(R.id.iv_avatar),authorHeadImgUrl)
            helper.setText(R.id.tv_language,productLang)
            helper.setText(R.id.tv_price, "¥$productPrice")

        }

        }
}