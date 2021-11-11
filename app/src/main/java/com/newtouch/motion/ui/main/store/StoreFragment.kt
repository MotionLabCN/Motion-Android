package com.newtouch.motion.ui.main.store

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.newtouch.motion.R
import com.newtouch.motion.adapter.FragmentListAdapter
import com.newtouch.motion.base.AppBaseFragment
import com.common.baselibrary.base.IBasePresenter
import com.newtouch.motion.adapter.TabStoreAdapter
import com.newtouch.motion.constants.Constants
import com.newtouch.motion.ui.main.store.fragment.StoreTabFragment1
import com.newtouch.motion.ui.main.store.fragment.StoreTabFragment2
import com.newtouch.motion.ui.main.store.fragment.StoreTabFragment3
import com.newtouch.motion.weight.indicator.OnTabClickListener
import kotlinx.android.synthetic.main.fragment_store.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator


class StoreFragment : AppBaseFragment<IBasePresenter<*>>(),OnTabClickListener {


    private var tabList = mutableListOf<String>()

    override fun init(savedInstanceState: Bundle?) {
//        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
//            llTop.elevation = 10f
//        }
        tabList.add(Constants.STORE_TYPE_1)
        tabList.add(Constants.STORE_TYPE_2)
        tabList.add(Constants.STORE_TYPE_3)
        initFragment()
    }

    private fun initFragment(){
        val fragmentList = mutableListOf<Fragment>()
        fragmentList.add(StoreTabFragment1())
        fragmentList.add(StoreTabFragment2())
        fragmentList.add(StoreTabFragment3())
        val adapter = FragmentListAdapter(fragmentList,childFragmentManager)
        viewPager.offscreenPageLimit = 6
        viewPager.adapter = adapter
        val commonNavigator = CommonNavigator(context)
        val tabAdapter = TabStoreAdapter(tabList)
        //tab点击事件
        tabAdapter.setOnTabClickListener(this)
        commonNavigator.adapter = tabAdapter
        magicView.navigator = commonNavigator
        //将magicView和viewPager进行绑定
        ViewPagerHelper.bind(magicView,viewPager)
    }

    override fun onTabClick(view: View, index: Int) {
        viewPager.currentItem = index
    }

    override fun createPresenter(): IBasePresenter<*>? {
        return null
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_store
    }

}
