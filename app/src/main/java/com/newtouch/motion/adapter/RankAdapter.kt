package com.newtouch.motion.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.newtouch.motion.R
import com.newtouch.motion.entity.RankEntity


class RankAdapter(layoutId:Int):BaseQuickAdapter<RankEntity.DatasBean,BaseViewHolder>(layoutId) {

    override fun convert(helper: BaseViewHolder, item: RankEntity.DatasBean?) {
        item?.apply {
            when(helper.adapterPosition){
                0->{
                    helper.setVisible(R.id.ivRank,true)
                    helper.setImageResource(R.id.ivRank,R.mipmap.gold_crown)
                    helper.setVisible(R.id.tvRank,false)
                    //占位符
                    helper.setText(R.id.tvRank,"1")

                }
                1->{
                    helper.setVisible(R.id.ivRank,true)
                    helper.setImageResource(R.id.ivRank,R.mipmap.silver_crown)
                    helper.setVisible(R.id.tvRank,false)
                    //占位符
                    helper.setText(R.id.tvRank,"1")
                }
                2->{
                    helper.setVisible(R.id.ivRank,true)
                    helper.setImageResource(R.id.ivRank,R.mipmap.cooper_crown)
                    helper.setVisible(R.id.tvRank,false)
                    //占位符
                    helper.setText(R.id.tvRank,"1")
                }
                else->{
                    helper.setVisible(R.id.ivRank,false)
                    helper.setText(R.id.tvRank,"${helper.adapterPosition+1}")
                    helper.setVisible(R.id.tvRank,true)
                }
            }
            helper.setText(R.id.tvName,username)
            helper.setText(R.id.tvIntegral,"$coinCount")
        }


    }
}