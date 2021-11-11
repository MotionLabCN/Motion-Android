package com.newtouch.motion.ui.main.search.fragment

import android.app.Dialog
import android.content.Intent
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
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
import com.newtouch.motion.entity.*
import com.newtouch.motion.http.HttpDefaultObserver
import com.newtouch.motion.http.RetrofitHelper
import com.newtouch.motion.ui.login.LoginForOtherActivity
import com.newtouch.motion.ui.main.MainActivity
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_code_energy.*
import org.w3c.dom.Text
import java.util.logging.Logger


/**
 * des 体系
 */
class CodeEnergyFragment : AppLazyFragment<CodeEnergyContract.Presenter<CodeEnergyContract.View>>(),
    CodeEnergyContract.View, OnSystemClickListener, OnRefreshListener, OnLoadMoreListener,
    BaseQuickAdapter.OnItemClickListener {

    private var systemAdapter: SystemAdapter? = null
    private var systemList: MutableList<SystemListEntity>? = null
    private var codeEnergyAdapter: CodeEnergyAdapter? = null
    private var mList = mutableListOf<String>()
    private var mCenterAdapter: CodeEnergyCenterAdapter? = null

    override fun createPresenter(): CodeEnergyContract.Presenter<CodeEnergyContract.View> {
        return CodeEnergyPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_code_energy
    }

    override fun lazyInit() {

        rvSystem.layoutManager = GridLayoutManager(context, 2)
        mList.add("1111")
        mList.add("222")
        mList.add("333")
        mList.add("444")
        mList.add("555")
        mList.add("666")
        codeEnergyAdapter = CodeEnergyAdapter()

        rvSystem.adapter = codeEnergyAdapter
        val inflate = layoutInflater.inflate(R.layout.item_header_code_energy, null)
        val ll_select = inflate.findViewById<LinearLayout>(R.id.ll_select)
        codeEnergyAdapter?.addHeaderView(inflate)
        val btn_send = inflate.findViewById<Button>(R.id.btn_send)
        btn_send.setOnClickListener {
        }
        ll_select.setOnClickListener {
            showDialog()
        }
        val rvOpenSource = inflate.findViewById<RecyclerView>(R.id.rvCenter)
        rvOpenSource.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        mCenterAdapter = CodeEnergyCenterAdapter()
        mCenterAdapter?.setNewData(mList)
        rvOpenSource.adapter = mCenterAdapter

        codeEnergyAdapter?.onItemClickListener = this


        smartRefresh?.setOnRefreshListener(this)
        smartRefresh?.setOnLoadMoreListener(this)


        loadingTip.loading()
        presenter?.getCodeEnergyList("", 0)
    }

    override fun showCodeEnergyList(list: CodeEnergyEntity) {
//        systemList = list
//        loadingTip.dismiss()
//        systemAdapter?.setNewData(list)
        loadingTip.dismiss()
        codeEnergyAdapter?.setNewData(list.content)
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

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        ToastUtils.show("==========codeenergy===" + mList[position])
    }


    private var codeDialog: Dialog? = null
    private fun showDialog() {
        if (null != codeDialog && codeDialog!!.isShowing) {
            codeDialog!!.dismiss()
        }
        codeDialog = Dialog(mContext, R.style.DialogTheme)
        val view = View.inflate(UIUtils.context, R.layout.dialog_select_menu, null)
        val tv_reset = view.findViewById<TextView>(R.id.tv_reset)
        val tv_used = view.findViewById<TextView>(R.id.tv_used)
        tv_reset.setOnClickListener {
            codeDialog?.dismiss()
        }
        tv_used.setOnClickListener {
//            codeDialog?.dismiss()
            showmenuDialog()
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

    private var menuDialog: Dialog? = null
    private fun showmenuDialog() {
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


