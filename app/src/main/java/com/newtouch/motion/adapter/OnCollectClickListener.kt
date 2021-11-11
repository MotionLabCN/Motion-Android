package com.newtouch.motion.adapter

import com.chad.library.adapter.base.BaseViewHolder


interface OnCollectClickListener {
    fun onCollectClick(helper: BaseViewHolder, position: Int)
}