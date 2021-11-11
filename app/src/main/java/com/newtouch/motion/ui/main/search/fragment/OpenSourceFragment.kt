package com.newtouch.motion.ui.main.search.fragment

import android.app.Dialog
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.common.baselibrary.utils.LogUtils
import com.common.baselibrary.utils.ToastUtils
import com.common.baselibrary.utils.UIUtils
import com.newtouch.motion.R
import com.newtouch.motion.adapter.*
import com.newtouch.motion.base.AppLazyFragment
import com.newtouch.motion.entity.HotCodeListEntity
import com.newtouch.motion.entity.OpenCodeCategoryEntity
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.fragment_open_source.*


/**
 * des 体系
 */
class OpenSourceFragment :
    AppLazyFragment<OpenSourceListContract.Presenter<OpenSourceListContract.View>>(),
    OpenSourceListContract.View, OnSystemClickListener, OnRefreshListener, OnLoadMoreListener,
    BaseQuickAdapter.OnItemChildClickListener {

    private var mAdapter: OpenSourceAdapter? = null
    private var mList = mutableListOf<String>()
    private var openSourceCenterAdapter: OpenSourceCenterAdapter? = null
    override fun createPresenter(): OpenSourceListContract.Presenter<OpenSourceListContract.View> {
        return OpenSourceListPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_open_source
    }

    override fun lazyInit() {

        rvSystem.layoutManager = LinearLayoutManager(context)
        mAdapter = OpenSourceAdapter()
        rvSystem.adapter = mAdapter

        val inflate = layoutInflater.inflate(R.layout.item_header_open_source, null)
        val ll_select = inflate.findViewById<LinearLayout>(R.id.ll_select)
        mAdapter?.addHeaderView(inflate)
        val rvOpenSource = inflate.findViewById<RecyclerView>(R.id.rvOpenSource)
        rvOpenSource.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        openSourceCenterAdapter = OpenSourceCenterAdapter()
        rvOpenSource.adapter = openSourceCenterAdapter
        openSourceCenterAdapter?.onItemChildClickListener = this

        smartRefresh?.setOnRefreshListener(this)
        smartRefresh?.setOnLoadMoreListener(this)

        loadingTip.loading()
//        presenter?.getCategoryList()
        presenter?.getHotCodeList("",1)
        presenter?.getNewStartList("",1)

        ll_select.setOnClickListener {
            showDialog()
        }
    }

    override fun showHotCodeList(data: MutableList<OpenCodeCategoryEntity>) {
            LogUtils.d("=======OpenCodeCategoryEntity==="+data.size)
    }

    override fun showHotCodeList(data: HotCodeListEntity) {

        loadingTip.dismiss()
        openSourceCenterAdapter?.setNewData(data.list)
    }

    override fun showNewStartList(data: HotCodeListEntity) {
        loadingTip.dismiss()
        mAdapter?.setNewData(data.list)

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

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        Log.d("=====", "====position====" + position)
    }

    private var selectDialog: Dialog? = null
    private fun showDialog() {
        if (null != selectDialog && selectDialog!!.isShowing) {
            selectDialog!!.dismiss()
        }
        selectDialog = Dialog(mContext, R.style.DialogTheme)
        val view = View.inflate(UIUtils.context, R.layout.dialog_select_menu, null)
        val tv_reset = view.findViewById<TextView>(R.id.tv_reset)
        val tv_used = view.findViewById<TextView>(R.id.tv_used)
        tv_reset.setOnClickListener {
            selectDialog?.dismiss()
        }
        tv_used.setOnClickListener {
//            codeDialog?.dismiss()
            menuDialog()
        }

        selectDialog?.setContentView(view)
        val window = selectDialog?.window
        window!!.setGravity(Gravity.BOTTOM)
        window.setWindowAnimations(R.style.main_menu_animStyle)
        window.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        selectDialog?.show()

    }

    private var menuDialog: Dialog? = null
    private fun menuDialog() {
        if (null != menuDialog && menuDialog!!.isShowing) {
            menuDialog!!.dismiss()
        }
        menuDialog = Dialog(mContext, R.style.DialogTheme)
        val view = View.inflate(UIUtils.context, R.layout.dialog_select_menu, null)

        menuDialog?.setContentView(view)
        val window = menuDialog?.window
        window!!.setGravity(Gravity.BOTTOM)
        window.setWindowAnimations(R.style.main_menu_animStyle)
        window.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        menuDialog?.show()

    }

}

