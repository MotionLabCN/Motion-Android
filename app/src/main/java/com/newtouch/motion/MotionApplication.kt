package com.newtouch.motion

import android.content.Context
import com.common.baselibrary.BaseApplication
import com.common.baselibrary.utils.ColorUtils
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.*
import com.scwang.smartrefresh.layout.footer.BallPulseFooter
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader


/**
 * des app
 */
class MotionApplication : BaseApplication() {
    companion object {
        //static 代码段可以防止内存泄露
//        init {
//            //设置全局的Header构建器
//            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
//                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white)//全局设置主题颜色
//                MaterialHeader(context)//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
//            }
//            //设置全局的Footer构建器
//            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
//                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white)//全局设置主题颜色
//                //指定为经典Footer，默认是 BallPulseFooter
//                val footer = BallPulseFooter(context)
//                footer.setAnimatingColor(ColorUtils.parseColor(R.color.theme))
//                footer.setBackgroundColor(ColorUtils.parseColor(R.color.white))
//                footer
//            }
//        }

        init {
            //设置全局的 Header 构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreator(object : DefaultRefreshHeaderCreator {
                override fun createRefreshHeader(
                    context: Context,
                    layout: RefreshLayout
                ): RefreshHeader {
                    //指定为经典Header，默认是 贝塞尔雷达 Header
                    return ClassicsHeader(context)
//                    .setPrimaryColor(ContextCompat.getColor(Companion.context, R.color.colorAccent))  // header 背景
                    //.setAccentColor(ContextCompat.getColor(Companion.context, R.color.black_f222)) // header 中文字，icon 颜色
                }
            })

            // 设置全局的 Footer 构建器
            SmartRefreshLayout.setDefaultRefreshFooterCreator(object : DefaultRefreshFooterCreator {
                override fun createRefreshFooter(
                    context: Context,
                    layout: RefreshLayout
                ): RefreshFooter {
//                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white)  //全局设置主题颜色
                    return ClassicsFooter(context)
                //指定为经典Header，默认是 贝塞尔雷达Header
//                    .setPrimaryColor(ContextCompat.getColor(Companion.context, R.color.colorAccent))  // footer 背景
                    //.setAccentColor(ContextCompat.getColor(Companion.context, R.color.black_f222)) // footer 中文字，icon 颜色
                }
            })


        }
    }
}
