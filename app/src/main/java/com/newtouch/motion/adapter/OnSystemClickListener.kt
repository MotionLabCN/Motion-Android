package com.newtouch.motion.adapter

import com.chad.library.adapter.base.BaseViewHolder


/**
 * 体系标签点击事件
 */
interface OnSystemClickListener {
    /**
     * @param i 外层角标
     * @param j 内层角标
     */
    fun onCollectClick(helper: BaseViewHolder, i: Int, j: Int)
}