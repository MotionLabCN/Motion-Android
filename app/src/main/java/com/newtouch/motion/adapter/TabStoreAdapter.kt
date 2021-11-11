package com.newtouch.motion.adapter

import android.content.Context
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import com.common.baselibrary.utils.ColorUtils
import com.common.baselibrary.widget.indicator.ZsSimplePagerTitleView
import com.newtouch.motion.R
import com.newtouch.motion.weight.indicator.OnTabClickListener
import net.lucode.hackware.magicindicator.buildins.UIUtil
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator



class TabStoreAdapter(tabList:MutableList<String>) : CommonNavigatorAdapter(){

    private var tabList = tabList

    private var onTabClickListener : OnTabClickListener? = null
    fun setOnTabClickListener(onTabClickListener : OnTabClickListener){
        this.onTabClickListener = onTabClickListener
    }

    override fun getTitleView(context: Context?, index: Int): IPagerTitleView {
        val simplePagerTitleView =
            ZsSimplePagerTitleView(context)
        simplePagerTitleView.text = tabList[index]
        simplePagerTitleView.textSize = 28f
        simplePagerTitleView.setPadding(30, 0, 30, 0)
        simplePagerTitleView.normalColor = ColorUtils.parseColor(R.color.Gray_600)
        simplePagerTitleView.selectedColor = ColorUtils.parseColor(R.color.Black_1000)
        simplePagerTitleView.setOnClickListener {
            onTabClickListener?.onTabClick(it,index)
        }
        //选中结果后将字体加粗
        simplePagerTitleView.setSelectListener(object : ZsSimplePagerTitleView.SelectListener{
            override fun onSelect(index: Int, totalCount: Int) {
                val tp = simplePagerTitleView.paint
                tp.isFakeBoldText = true
//                simplePagerTitleView.textSize = 26f
            }

            override fun onDeselected(index: Int, totalCount: Int) {
                val tp = simplePagerTitleView.paint
                tp.isFakeBoldText = false
//                simplePagerTitleView.textSize = 28f
            }
        })
        return simplePagerTitleView
    }

    override fun getCount(): Int {
        return tabList.size
    }

    override fun getIndicator(context: Context?): IPagerIndicator {
        val indicator = LinePagerIndicator(context)
        indicator.mode = LinePagerIndicator.MODE_WRAP_CONTENT
        indicator.lineHeight = UIUtil.dip2px(context, 3.0).toFloat()
        indicator.lineWidth = UIUtil.dip2px(context, 20.0).toFloat()
        indicator.roundRadius = UIUtil.dip2px(context, 3.0).toFloat()
        indicator.startInterpolator = AccelerateInterpolator()
        indicator.endInterpolator = DecelerateInterpolator(2.0f)
        indicator.setColors(ColorUtils.parseColor(R.color.transparent))
        return indicator
    }
}