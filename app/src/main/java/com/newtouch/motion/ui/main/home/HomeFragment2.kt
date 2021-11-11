package com.newtouch.motion.ui.main.home

import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import cn.bingoogolapple.bgabanner.BGABanner
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.common.baselibrary.utils.LogUtils
import com.common.baselibrary.utils.PrefUtils
import com.common.baselibrary.utils.ToastUtils
import com.common.baselibrary.utils.UIUtils
import com.newtouch.motion.R
import com.newtouch.motion.adapter.ArticleAdapter
import com.newtouch.motion.adapter.HomeWeiboAdapter
import com.newtouch.motion.entity.BannerEntity
import com.newtouch.motion.entity.ArticleEntity
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.newtouch.motion.adapter.OnCollectClickListener
import com.newtouch.motion.base.AppLazyFragment
import com.newtouch.motion.constants.Constants
import com.newtouch.motion.event.LoginEvent
import com.newtouch.motion.event.LogoutEvent
import com.newtouch.motion.proxy.ImageLoad
import com.newtouch.motion.ui.login.LoginActivity
import com.newtouch.motion.ui.login.LoginActivity1
import com.newtouch.motion.ui.search.SearchActivity
import com.newtouch.motion.ui.web.WebActivity
import com.newtouch.motion.utils.AppManager
import com.newtouch.motion.weight.ReloadListener
import kotlinx.android.synthetic.main.fragment_home2.*
import kotlinx.android.synthetic.main.layout_public_toolbar.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


/**
 * 首页
 *
 */
class HomeFragment2 : AppLazyFragment<HomeContract.Presenter<HomeContract.View>>(), HomeContract.View,OnLoadMoreListener,OnRefreshListener,ReloadListener
,BaseQuickAdapter.OnItemClickListener, OnCollectClickListener {
    private var pageNum:Int = 0
    private var articleList = mutableListOf<ArticleEntity.DatasBean>()

    private var articleAdapter: ArticleAdapter? = null
    private var currentPosition = 0

    private var weiboAdapter:HomeWeiboAdapter?= null
    private var mList = mutableListOf<String>()
    /**
     * 点击收藏后将点击事件上锁,等接口有相应结果再解锁
     * 避免重复点击产生的bug
     */
    private var lockCollectClick = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun lazyInit() {
        initView()
        loadingTip.loading()
        loadData()
    }

    private fun initView(){
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
//            rlSearch.elevation = 10f
//            llRadius.elevation = 20f
            rvHomeList.isNestedScrollingEnabled = false
        }

//        articleAdapter = ArticleAdapter(articleList)
//        articleAdapter?.onItemClickListener = this
//        articleAdapter?.setCollectClickListener(this)
//        articleAdapter?.setNewData(articleList)
//        rvHomeList.adapter = articleAdapter
//        loadingTip.setReloadListener(this)
//        smartRefresh?.setOnRefreshListener(this)
//        smartRefresh?.setOnLoadMoreListener(this)
//        addScrollListener()
//        rvHomeList.layoutManager = LinearLayoutManager(context)
//        ivSearch.setOnClickListener{
//            intent(SearchActivity::class.java,false)
//            //瞬间开启activity，无动画
//            activity?.overridePendingTransition(0, 0)
//        }

        weiboAdapter = HomeWeiboAdapter()
        mList.add("1111")
        mList.add("222")
        mList.add("333")
        mList.add("444")
        mList.add("555")
        mList.add("666")
        weiboAdapter?.setNewData(mList)
        rvHomeList.adapter = weiboAdapter
        loadingTip.setReloadListener(this)
        smartRefresh?.setOnRefreshListener(this)
        smartRefresh?.setOnLoadMoreListener(this)
        addScrollListener()
        rvHomeList.layoutManager = LinearLayoutManager(context)


        smartRefresh.visibility = View.GONE
        ll_search_friends.visibility = View.VISIBLE

        tv_search_friend.setOnClickListener {
            startActivity(Intent(context, LoginActivity1::class.java))

            LogUtils.d("====tv_search_friend====="+PrefUtils.getString(Constants.ACCESS_TOKEN))
        }

        ci_user.setOnClickListener {
            showDialog()
        }
    }

    /**
     * 加载数据
     * 初始化，网络出错重新加载，刷新均可使用
     */
    private fun loadData(){
        articleList.clear()
        articleAdapter?.setNewData(articleList)
        pageNum = 0
        presenter?.loadData(pageNum)
    }

    /**
     * 为NestedScrollView增加滑动事件
     * 改变搜索框的透明度
     */
    private fun addScrollListener(){
//        nestedView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener
//        { _, _, scrollY, _, _ ->
//            val alpha = if (scrollY>0){
//                ivSearch.isEnabled = true
//                scrollY.toFloat() / (300).toFloat()
//            }else{
//                ivSearch.isEnabled = false
//                0f
//            }
//            rlSearch.alpha = alpha
//        })
    }


    /**
     * 文章列表加载成功
     */
    override fun showList(list: MutableList<ArticleEntity.DatasBean>) {
        dismissRefresh()
        loadingTip.dismiss()
        if (list.isNotEmpty()){
            articleList.addAll(list)
            articleAdapter?.setNewData(articleList)
        }else {
            if (articleList.size==0)loadingTip.showEmpty()
            else ToastUtils.show("没有数据啦...")
        }
    }

    override fun showBanner(bannerList:MutableList<BannerEntity>) {
    }

    override fun unCollectSuccess() {
        lockCollectClick = true
        if (currentPosition<articleList.size) {
            articleList[currentPosition].collect = false
            articleAdapter?.notifyItemChanged(currentPosition)
        }
    }

    override fun collectSuccess() {
        lockCollectClick = true
        if (currentPosition<articleList.size) {
            articleList[currentPosition].collect = true
            articleAdapter?.notifyItemChanged(currentPosition)
        }
    }

    override fun onError(error: String) {
        lockCollectClick = true
        //请求失败将page -1
        if (pageNum>0)pageNum--
        loadingTip.dismiss()
        dismissRefresh()
        ToastUtils.show(error)
    }

    /**
     * 加载更多
     */
    override fun onLoadMore(refreshLayout: RefreshLayout) {
        pageNum++
        presenter?.loadData(pageNum)
    }

    /**
     * 刷新
     */
    override fun onRefresh(refreshLayout: RefreshLayout) {
        loadData()
    }

    /**
     * 无网络，重新加载
     */
    override fun reload() {
        loadingTip.loading()
        loadData()
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        intent(Bundle().apply {
            putString(Constants.WEB_URL,articleList[position].link)
            putString(Constants.WEB_TITLE,articleList[position].title)
        },WebActivity::class.java,false)
    }

    /**
     * 收藏点击
     */
    override fun onCollectClick(helper: BaseViewHolder, position: Int) {
        if (!AppManager.isLogin()) {
            ToastUtils.show("请先登录")
            return
        }
        if (position<articleList.size&&lockCollectClick){
            lockCollectClick = false
            //记录当前点击的item
            currentPosition = position
            //收藏状态调用取消收藏接口，反之亦然
            articleList[position].apply {
                if (collect) presenter?.unCollect(id)
                else presenter?.collect(id)
            }
        }
    }


    /**
     * 隐藏刷新加载
     */
    private fun dismissRefresh() {
        if (smartRefresh.state.isOpening) {
            smartRefresh.finishLoadMore()
            smartRefresh.finishRefresh()
        }
    }

    override fun createPresenter(): HomeContract.Presenter<HomeContract.View>? {
        return HomePresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home2
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    /**
     * 登陆消息，更新收藏状态
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun loginEvent(loginEvent: LoginEvent){

    }

    /**
     * 退出消息
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun logoutEvent(loginEvent: LogoutEvent){
        articleList.forEach {
            it.collect = false
        }
        articleAdapter?.notifyDataSetChanged()
    }


    private var codeDialog: Dialog? = null
    private fun showDialog() {
        if (null != codeDialog && codeDialog!!.isShowing){
            codeDialog!!.dismiss()
        }
        codeDialog = Dialog(mContext, R.style.DialogTheme)
        val view = View.inflate(UIUtils.context, R.layout.dialog_mine, null)
        val mLoginPhone = view.findViewById<TextView>(R.id.tv_login_for_phone)
        val iv_close = view.findViewById<ImageView>(R.id.iv_close)

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
