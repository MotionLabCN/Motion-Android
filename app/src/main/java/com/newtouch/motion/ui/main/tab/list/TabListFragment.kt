package com.newtouch.motion.ui.main.tab.list


import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.common.baselibrary.utils.ToastUtils
import com.newtouch.motion.R
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.newtouch.motion.adapter.ArticleAdapter
import com.newtouch.motion.adapter.OnCollectClickListener
import com.newtouch.motion.base.AppLazyFragment
import com.newtouch.motion.constants.Constants
import com.newtouch.motion.entity.ArticleEntity
import com.newtouch.motion.ui.web.WebActivity
import com.newtouch.motion.utils.AppManager
import com.newtouch.motion.weight.ReloadListener
import kotlinx.android.synthetic.main.fragment_article_list.*


class TabListFragment : AppLazyFragment<TabListContract.Presenter<TabListContract.View>>()
,TabListContract.View,OnCollectClickListener,BaseQuickAdapter.OnItemClickListener
    , OnLoadMoreListener, OnRefreshListener, ReloadListener {

    private var projectList = mutableListOf<ArticleEntity.DatasBean>()
    private var pageNum = 1
    private var projectId : Int = 0
    private var name:String? = null
    private var projectAdapter:ArticleAdapter? = null
    private var currentPosition = 0
    private var type:Int? = null
    override fun lazyInit() {
        type = arguments?.getInt("type")
        projectId = arguments?.getInt("id") ?: 0
        name = arguments?.getString("name") ?: ""
        initView()
        loadingTip.loading()
        loadData()
    }

    private fun initView(){
        loadingTip.setReloadListener(this)
        smartRefresh?.setOnRefreshListener(this)
        smartRefresh?.setOnLoadMoreListener(this)
        projectAdapter = ArticleAdapter(projectList)
        projectAdapter?.setCollectClickListener(this)
        projectAdapter?.onItemClickListener = this
        rvProject.layoutManager = LinearLayoutManager(context)
        rvProject.adapter = projectAdapter
    }

    private fun loadData(){
        projectList.clear()
        projectAdapter?.setNewData(projectList)
        pageNum = 1
        type?.let { presenter?.loadData(it,projectId,pageNum) }
    }

    override fun showList(list: MutableList<ArticleEntity.DatasBean>) {
        dismissRefresh()
        loadingTip.dismiss()
        if (list.isNotEmpty()){
            projectList.addAll(list)
            projectAdapter?.setNewData(projectList)
        }else {
            if (projectList.size==0) loadingTip.showEmpty()
            else ToastUtils.show("???????????????...")
        }
    }

    /**
     * ????????????
     */
    override fun collectSuccess() {
        if (currentPosition<projectList.size) {
            projectList[currentPosition].collect = true
            projectAdapter?.notifyItemChanged(currentPosition)
        }
    }

    /**
     * ??????????????????
     */
    override fun unCollectSuccess() {
        if (currentPosition<projectList.size) {
            projectList[currentPosition].collect = false
            projectAdapter?.notifyItemChanged(currentPosition)
        }
    }

    override fun onError(error: String) {
        //???????????????page -1
        if (pageNum>1)pageNum--
        dismissRefresh()
        loadingTip.dismiss()
        ToastUtils.show(error)
    }

    /**
     * ????????????
     */
    override fun onCollectClick(helper: BaseViewHolder, position: Int) {
        if (!AppManager.isLogin()) {
            ToastUtils.show("????????????")
            return
        }
        if (position<projectList.size){
            //?????????????????????item
            currentPosition = position
            //???????????????????????????????????????????????????
            projectList[position].apply {
                if (collect) presenter?.unCollect(id)
                else presenter?.collect(id)
            }
        }
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        intent(Bundle().apply {
            putString(Constants.WEB_URL,projectList[position].link)
            putString(Constants.WEB_TITLE,projectList[position].title)
        }, WebActivity::class.java,false)
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        pageNum++
        type?.let { presenter?.loadData(it,id,pageNum) }
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        loadData()
    }

    /**
     * ????????????
     */
    override fun reload() {
        loadingTip.loading()
        loadData()
    }

    override fun createPresenter(): TabListContract.Presenter<TabListContract.View>? {
        return TabListPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_article_list
    }

    /**
     * ??????????????????
     */
    private fun dismissRefresh() {
        if (smartRefresh.state.isOpening) {
            smartRefresh.finishLoadMore()
            smartRefresh.finishRefresh()
        }
    }


}
