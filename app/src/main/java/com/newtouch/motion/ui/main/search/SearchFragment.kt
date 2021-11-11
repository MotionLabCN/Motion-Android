package com.newtouch.motion.ui.main.search

import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.newtouch.motion.R
import com.newtouch.motion.adapter.FragmentListAdapter
import com.newtouch.motion.adapter.TabAdapter
import com.newtouch.motion.base.AppBaseFragment
import com.common.baselibrary.base.IBasePresenter
import com.common.baselibrary.utils.ToastUtils
import com.common.baselibrary.utils.UIUtils
import com.newtouch.motion.constants.Constants
import com.newtouch.motion.ui.login.LoginActivity1
import com.newtouch.motion.ui.login.LoginForOtherActivity
import com.newtouch.motion.ui.main.MainActivity
import com.newtouch.motion.ui.main.search.fragment.CodeEnergyFragment
import com.newtouch.motion.ui.main.search.fragment.HotTopicsFragment
import com.newtouch.motion.ui.main.search.fragment.OpenSourceFragment
import com.newtouch.motion.ui.set.SettingActivity
import com.newtouch.motion.weight.indicator.OnTabClickListener
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.layout_search_toolbar.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator



class SearchFragment : AppBaseFragment<IBasePresenter<*>>(),OnTabClickListener {


    private var tabList = mutableListOf<String>()

    override fun init(savedInstanceState: Bundle?) {
//        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
//            llTop.elevation = 10f
//        }
        tabList.add(Constants.SEARCH_TYPE_1)
        tabList.add(Constants.SEARCH_TYPE_2)
        tabList.add(Constants.SEARCH_TYPE_3)
        tabList.add(Constants.SEARCH_TYPE_4)
        initFragment()

        ci_user.setOnClickListener{
        showDialog()
        }
        iv_menu_icon.setOnClickListener {

        }
    }

    private fun initFragment(){
        val fragmentList = mutableListOf<Fragment>()
        fragmentList.add(CodeEnergyFragment())
        fragmentList.add(CodeEnergyFragment())
        fragmentList.add(OpenSourceFragment())
        fragmentList.add(HotTopicsFragment())
        val adapter = FragmentListAdapter(fragmentList,childFragmentManager)
        viewPager.offscreenPageLimit = 6
        viewPager.adapter = adapter
        val commonNavigator = CommonNavigator(context)
        val tabAdapter = TabAdapter(tabList)
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
        return R.layout.fragment_search
    }

    private var codeDialog: Dialog? = null
    private fun showDialog() {
        if (null != codeDialog && codeDialog!!.isShowing){
            codeDialog!!.dismiss()
        }
        codeDialog = Dialog(mContext, R.style.DialogTheme)
        val view = View.inflate(UIUtils.context, R.layout.dialog_mine, null)
        val ll_setting = view.findViewById<LinearLayout>(R.id.ll_setting)
        val iv_close = view.findViewById<ImageView>(R.id.iv_close)
        ll_setting.setOnClickListener{
            startActivity(Intent(context, SettingActivity::class.java))

        }

        iv_close.setOnClickListener{
            codeDialog?.dismiss()
        }

        codeDialog?.setContentView(view)
        val window = codeDialog?.window
        window!!.setGravity(Gravity.BOTTOM)
        window.setWindowAnimations(R.style.main_menu_animStyle)
        window.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        codeDialog?.show()

    }
}
