package com.newtouch.motion.ui.main.store.fragment

import com.chad.library.adapter.base.BaseViewHolder
import com.common.baselibrary.utils.ToastUtils
import com.newtouch.motion.R
import com.newtouch.motion.adapter.CodeEnergyAdapter
import com.newtouch.motion.adapter.OnSystemClickListener
import com.newtouch.motion.base.AppLazyFragment
import com.newtouch.motion.entity.SystemListEntity
import com.newtouch.motion.ui.main.system.list.SystemListContract
import com.newtouch.motion.ui.main.system.list.SystemListPresenter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener



class StoreTabFragment3 : AppLazyFragment<SystemListContract.Presenter<SystemListContract.View>>()
    ,SystemListContract.View ,OnSystemClickListener, OnRefreshListener, OnLoadMoreListener {

    private var codeEnergyAdapter :CodeEnergyAdapter?=null
    private var mList = mutableListOf<String>()

    override fun createPresenter():SystemListContract.Presenter<SystemListContract.View> {
        return SystemListPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_store_tab_3
    }

    override fun lazyInit() {


    }

    override fun showList(list: MutableList<SystemListEntity>) {
//        systemList = list
//        loadingTip.dismiss()
//        systemAdapter?.setNewData(list)
    }

    override fun onError(error: String) {

        ToastUtils.show(error)
    }

    override fun onCollectClick(helper: BaseViewHolder, i: Int, j: Int) {

    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        refreshLayout.finishRefresh(3)
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        refreshLayout.finishLoadMore(3)
    }


}
