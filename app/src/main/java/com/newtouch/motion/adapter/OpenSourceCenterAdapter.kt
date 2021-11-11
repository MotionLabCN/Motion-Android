package com.newtouch.motion.adapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.newtouch.motion.R
import com.newtouch.motion.entity.HotCodeListEntity
import com.newtouch.motion.proxy.ImageLoad

/**
 * 文章适配器
 */
class OpenSourceCenterAdapter() : BaseQuickAdapter<HotCodeListEntity.DataBean, BaseViewHolder>(R.layout.item_open_souce_center) {
    private var collectClickListener:OnCollectClickListener? = null

    fun setCollectClickListener(collectClickListener:OnCollectClickListener){
        this.collectClickListener = collectClickListener
    }


    override fun convert(helper: BaseViewHolder, item: HotCodeListEntity.DataBean?) {

            item?.apply {
                ImageLoad.load(helper.getView(R.id.iv_avatar),avatarUrl)
                helper.setText(R.id.tv_name,language)
                helper.setText(R.id.tv_start,""+starCount)

            }


        }

}