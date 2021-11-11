package com.newtouch.motion.ui.set

import android.content.Context
import android.os.Bundle
import android.view.View
import com.common.baselibrary.utils.CacheDataManager
import com.common.baselibrary.utils.ColorUtils
import com.common.baselibrary.utils.StatusUtils
import com.common.baselibrary.utils.ToastUtils
import com.newtouch.motion.R
import com.newtouch.motion.base.AppBaseActivity
import com.newtouch.motion.constants.Constants
import com.newtouch.motion.constants.UrlConstants
import com.newtouch.motion.utils.DialogUtils
import com.newtouch.motion.proxy.IConfirmClickCallBack
import com.newtouch.motion.ui.login.LoginActivity
import com.newtouch.motion.ui.web.WebActivity
import com.newtouch.motion.utils.AppManager
import kotlinx.android.synthetic.main.activity_set.*

class SetActivity : AppBaseActivity<SetContract.Presenter<SetContract.View>>(),SetContract.View ,
    View.OnClickListener {


    override fun init(savedInstanceState: Bundle?) {
        ivBack.setOnClickListener(this)
        tvClear.setOnClickListener(this)
        tvVersion.setOnClickListener(this)
        tvAuthor.setOnClickListener(this)
        tvProject.setOnClickListener(this)
        tvCopyright.setOnClickListener(this)
        tvLogout.setOnClickListener(this)
        tvClearValue.text = CacheDataManager.getTotalCacheSize(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            //清除缓存
            R.id.ivBack->finish()
            //清除缓存
            R.id.tvClear->{
                DialogUtils.confirm(this,"缓存中可能包含小姐姐照片哦，是否确定清除?",object : IConfirmClickCallBack {
                    override fun onClick() {
                        CacheDataManager.clearAllCache(this@SetActivity)
                        tvClearValue.text = ""
                        ToastUtils.show("已清除")
                    }
                })
            }
            //版本
            R.id.tvVersion-> ToastUtils.show("已是最新版本")
            //关于作者
            R.id.tvAuthor->{
                DialogUtils.author(this)
            }
            //项目
            R.id.tvProject->{
                intent(Bundle().apply {
                    putString(Constants.WEB_URL, UrlConstants.APP_GITHUB)
                    putString(Constants.WEB_TITLE,Constants.APP_NAME)
                }, WebActivity::class.java,false)
            }
            //版权
            R.id.tvCopyright->{
                DialogUtils.tips(this,Constants.COPYRIGHT,object : IConfirmClickCallBack {
                    override fun onClick() {
                    }
                })
            }
            //退出登录
            R.id.tvLogout->{
                DialogUtils.confirm(this,"是否确定退出?",object : IConfirmClickCallBack {
                    override fun onClick() {
                        presenter?.logout()
                    }
                })

            }
        }
    }

    override fun createPresenter(): SetContract.Presenter<SetContract.View>? {
        return SetPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_set
    }

    /**
     * 退出登陆成功，清除用户状态
     */
    override fun logoutSuccess() {
        finish()
        ToastUtils.show("已退出登录")
        AppManager.resetUser()
        intent(LoginActivity::class.java,false)
    }

    override fun getContext(): Context? {
        return this
    }

    override fun onError(error: String) {
        ToastUtils.show(error)
    }

    /**
     * 设置状态栏背景颜色
     */
    override fun setStatusColor() {
        StatusUtils.setUseStatusBarColor(this, ColorUtils.parseColor("#fff087"))
    }



}
