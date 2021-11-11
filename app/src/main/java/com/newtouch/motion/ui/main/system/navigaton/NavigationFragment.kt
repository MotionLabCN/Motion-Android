package com.newtouch.motion.ui.main.system.navigaton


import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseViewHolder
import com.common.baselibrary.utils.ToastUtils

import com.newtouch.motion.R
import com.newtouch.motion.adapter.NavigationAdapter
import com.newtouch.motion.adapter.OnSystemClickListener
import com.newtouch.motion.base.AppLazyFragment
import com.newtouch.motion.constants.Constants
import com.newtouch.motion.entity.NavigationEntity
import com.newtouch.motion.ui.web.WebActivity
import kotlinx.android.synthetic.main.fragment_system_list.*

/**
 * 导航列表
 */
class NavigationFragment  : AppLazyFragment<NavigationContract.Presenter<NavigationContract.View>>()
    , NavigationContract.View , OnSystemClickListener {

    private var navigationList = mutableListOf<NavigationEntity>()
    private var navigationAdapter: NavigationAdapter? = null

    override fun lazyInit() {
        rvSystem.layoutManager = LinearLayoutManager(context)
        navigationAdapter = NavigationAdapter(R.layout.item_system)
        navigationAdapter?.setOnSystemClickListener(this)
        rvSystem.adapter = navigationAdapter
        loadingTip.loading()
        presenter?.loadData()
    }

    override fun showList(list: MutableList<NavigationEntity>) {
        loadingTip.dismiss()
        navigationList.addAll(list)
        navigationAdapter?.setNewData(navigationList)
    }

    override fun onError(error: String) {
        loadingTip.dismiss()
        ToastUtils.show(error)
    }

    override fun onCollectClick(helper: BaseViewHolder, i: Int, j: Int) {
        intent(Bundle().apply {
            putString(Constants.WEB_URL,navigationList[i].articles[j].link)
            putString(Constants.WEB_TITLE,navigationList[i].articles[j].title)
        }, WebActivity::class.java,false)
    }


    override fun createPresenter(): NavigationContract.Presenter<NavigationContract.View> {
        return NavigationPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_system_list
    }

}
