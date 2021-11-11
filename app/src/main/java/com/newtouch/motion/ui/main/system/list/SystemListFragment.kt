package com.newtouch.motion.ui.main.system.list

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseViewHolder
import com.common.baselibrary.utils.ToastUtils
import com.newtouch.motion.R
import com.newtouch.motion.adapter.OnSystemClickListener
import com.newtouch.motion.adapter.SystemAdapter
import com.newtouch.motion.base.AppLazyFragment
import com.newtouch.motion.constants.Constants
import com.newtouch.motion.entity.SystemListEntity
import com.newtouch.motion.ui.main.system.activity.SystemActivity
import kotlinx.android.synthetic.main.fragment_system_list.*



class SystemListFragment : AppLazyFragment<SystemListContract.Presenter<SystemListContract.View>>()
    ,SystemListContract.View ,OnSystemClickListener{

    private var systemAdapter:SystemAdapter? = null
    private var systemList: MutableList<SystemListEntity>? = null
    override fun lazyInit() {
        rvSystem.layoutManager = LinearLayoutManager(context)
        systemAdapter = SystemAdapter(R.layout.item_system)
        systemAdapter?.setOnSystemClickListener(this)
        rvSystem.adapter = systemAdapter
        loadingTip.loading()
        presenter?.loadData()
    }

    override fun showList(list: MutableList<SystemListEntity>) {
        systemList = list
        loadingTip.dismiss()
        systemAdapter?.setNewData(list)
    }

    override fun onError(error: String) {
        loadingTip.dismiss()
        ToastUtils.show(error)
    }

    override fun onCollectClick(helper: BaseViewHolder, i: Int, j: Int) {
        val id = systemList?.get(i)?.children?.get(j)?.id
        val title = systemList?.get(i)?.children?.get(j)?.name

        intent(Bundle().apply {
            id?.let { putInt(Constants.SYSTEM_ID, it) }
            putString(Constants.SYSTEM_TITLE, title)
        }, SystemActivity::class.java,false)
    }

    override fun createPresenter():SystemListContract.Presenter<SystemListContract.View> {
        return SystemListPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_system_list
    }

}
