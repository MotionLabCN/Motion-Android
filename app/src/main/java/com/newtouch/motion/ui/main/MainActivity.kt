package com.newtouch.motion.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Lifecycle
import com.common.baselibrary.utils.ToastUtils
import com.newtouch.motion.R
import com.newtouch.motion.base.AppBaseActivity
import com.common.baselibrary.base.IBasePresenter
import com.common.baselibrary.base.IBaseView
import com.newtouch.motion.constants.Constants
import com.newtouch.motion.ui.main.home.HomeFragment
import com.newtouch.motion.ui.main.home.HomeFragment2

import com.newtouch.motion.ui.main.search.SearchFragment
import com.newtouch.motion.ui.main.store.StoreFragment
import com.newtouch.motion.ui.main.tab.TabFragment
import com.newtouch.motion.ui.main.system.SystemFragment
import kotlinx.android.synthetic.main.activity_main.*


/**
 * activity基础类
 *
 */
class MainActivity : AppBaseActivity<IBasePresenter<*>>(), IBaseView {

    private var lastIndex = 0
    private var fragments: MutableList<Fragment> = mutableListOf()

    override fun init(savedInstanceState: Bundle?) {
        initFragment()
        initBottom()
    }

    private fun initFragment() {
//        //首页
//        fragments.add(HomeFragment())
//        //体系
//        fragments.add(SystemFragment())
//
//        //项目
//        val project = TabFragment()
//        val proBundle = Bundle()
//        proBundle.putInt("type", Constants.PROJECT_TYPE)
//        project.arguments = proBundle
//        fragments.add(project)
//
//        //公众号
//        val account = TabFragment()
//        val accountBundle = Bundle()
//        accountBundle.putInt("type", Constants.ACCOUNT_TYPE)
//        account.arguments = accountBundle
//        fragments.add(account)
//        //我的
//        fragments.add(MineFragment())
//        setFragmentPosition(0)



        fragments.add(HomeFragment2())
        fragments.add(SearchFragment())
        fragments.add(StoreFragment())

        setFragmentPosition(0)
    }

    private fun initBottom() {
        btmNavigation.run {
            setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_home -> setFragmentPosition(0)
                    R.id.menu_project -> setFragmentPosition(1)
                    R.id.menu_square -> setFragmentPosition(2)
//                    R.id.menu_official_account -> setFragmentPosition(3)
//                    R.id.menu_mine -> setFragmentPosition(3)
                }
                // 这里注意返回true,否则点击失效
                true
            }
        }
    }

    private fun setFragmentPosition(position: Int) {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        val currentFragment: Fragment = fragments[position]
        val lastFragment: Fragment = fragments[lastIndex]
        lastIndex = position
        ft.hide(lastFragment)
        if (!currentFragment.isAdded) {
            supportFragmentManager.beginTransaction().remove(currentFragment).commit()
            ft.add(R.id.frameLayout, currentFragment)
            ft.setMaxLifecycle(currentFragment, Lifecycle.State.RESUMED)
        }
        ft.show(currentFragment)
        ft.commit()
    }

    override fun createPresenter(): IBasePresenter<*>? {
        return null
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getContext(): Context? {
        return this
    }

    override fun onError(error: String) {

    }

    var lastTime: Long = 0
    override fun onBackPressed() {
        if (System.currentTimeMillis() - this.lastTime > 2000L) {
            ToastUtils.show("再按一次退出程序")
            this.lastTime = System.currentTimeMillis()
            return
        } else {
            super.onBackPressed()
        }
    }
}
