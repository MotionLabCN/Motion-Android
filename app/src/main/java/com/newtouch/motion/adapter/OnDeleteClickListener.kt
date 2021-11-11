package com.newtouch.motion.adapter

import com.chad.library.adapter.base.BaseViewHolder

/**
 * des 删除回调
 */
interface OnDeleteClickListener {
    fun onDeleteClick(helper: BaseViewHolder, position: Int)
}