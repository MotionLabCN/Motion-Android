package com.newtouch.motion.adapter

import android.text.Html
import android.text.TextUtils
import android.widget.ImageView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.newtouch.motion.R
import com.newtouch.motion.constants.Constants




class HomeWeiboAdapter: BaseQuickAdapter<String, BaseViewHolder>( R.layout.item_home_weibo) {



    private var collectClickListener:OnCollectClickListener? = null

    fun setCollectClickListener(collectClickListener:OnCollectClickListener){
        this.collectClickListener = collectClickListener
    }

    override fun convert(helper: BaseViewHolder, item: String?) {
        item?.apply {
//                    ImageLoad.loadRadius(helper.getView(R.id.ivTitle),envelopePic,20)
//                    helper.setText(R.id.tvTitle,title)
//                    helper.setText(R.id.tvDes,desc)
//                    helper.setText(R.id.tvNameData,"$niceDate | $author")
//                    helper.getView<ImageView>(R.id.ivCollect).apply {
//                        setOnClickListener(OnLimitClickHelper(OnLimitClickListener {
//                            collectClickListener?.onCollectClick(helper, helper.adapterPosition)
//                        }))
//                        if (item.collect) {
//                            setImageResource(R.mipmap.article_collect)
//                        }else{
//                            setImageResource(R.mipmap.article_un_collect)
//                        }
//                    }
                }
    }
}