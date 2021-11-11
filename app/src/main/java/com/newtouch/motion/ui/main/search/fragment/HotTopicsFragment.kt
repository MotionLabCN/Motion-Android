package com.newtouch.motion.ui.main.search.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.common.baselibrary.utils.ToastUtils
import com.newtouch.motion.R
import com.newtouch.motion.adapter.*
import com.newtouch.motion.base.AppLazyFragment
import com.newtouch.motion.constants.Constants
import com.newtouch.motion.entity.SystemListEntity
import com.newtouch.motion.ui.main.system.activity.SystemActivity
import com.newtouch.motion.ui.main.system.list.SystemListContract
import com.newtouch.motion.ui.main.system.list.SystemListPresenter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.fragment_home2.*
import kotlinx.android.synthetic.main.fragment_system_list.*
import kotlinx.android.synthetic.main.fragment_system_list.loadingTip
import java.util.logging.Logger



class HotTopicsFragment : AppLazyFragment<SystemListContract.Presenter<SystemListContract.View>>()
    ,SystemListContract.View ,OnSystemClickListener, OnRefreshListener, OnLoadMoreListener {

    private var mAdapter : HotTopicsAdapter?=null
    private var mList = mutableListOf<String>()

    override fun createPresenter():SystemListContract.Presenter<SystemListContract.View> {
        return SystemListPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_open_source
    }

    override fun lazyInit() {

        rvSystem.layoutManager = LinearLayoutManager(context)
        mList.add("1111")
        mList.add("222")
        mList.add("333")
        mList.add("444")
        mList.add("555")
        mList.add("666")
        mList.add("666")
        mList.add("666")
        mList.add("666")
        mList.add("666")
        mAdapter = HotTopicsAdapter()
        mAdapter?.setNewData(mList)
        rvSystem.adapter = mAdapter
        val inflate = layoutInflater.inflate(R.layout.item_header_hot_topics, null)
        mAdapter?.addHeaderView(inflate)

        smartRefresh?.setOnRefreshListener(this)
        smartRefresh?.setOnLoadMoreListener(this)

//        loadingTip.loading()
//        presenter?.loadData()
    }

    override fun showList(list: MutableList<SystemListEntity>) {
//        systemList = list
//        loadingTip.dismiss()
//        systemAdapter?.setNewData(list)
    }

    override fun onError(error: String) {
        loadingTip.dismiss()
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
