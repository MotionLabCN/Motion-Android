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
import com.newtouch.motion.proxy.IConfirmClickCallBack
import com.newtouch.motion.ui.login.LoginActivity
import com.newtouch.motion.utils.AppManager
import com.newtouch.motion.utils.DialogUtils
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.layout_public_toolbar.*

/**
 * Created by Dan on 2021/10/19.
 */
class SettingActivity : AppBaseActivity<SetContract.Presenter<SetContract.View>>(),SetContract.View ,
    View.OnClickListener {

    override fun createPresenter(): SetContract.Presenter<SetContract.View>? {
        return SetPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_setting
    }
    override fun init(savedInstanceState: Bundle?) {

        ci_user.visibility = View.GONE
        iv_title_logo.visibility = View.GONE
        iv_title_right.visibility = View.GONE

        tv_title.visibility = View.VISIBLE
        tv_title_right.visibility = View.VISIBLE
        tv_title.text = "设置"
        tv_title_right.text = "完成"
        tv_title_right.setOnClickListener {
            finish()
        }

    }

    override fun onClick(v: View?) {
        when(v?.id){
            //清除缓存
            R.id.ivBack->finish()
            //清除缓存
            R.id.tvClear->{

            }
            //关于作者
            R.id.tvAuthor->{
                DialogUtils.author(this)
            }
            //项目
            R.id.tvProject->{

            }
            //版权
            R.id.tvCopyright->{
                DialogUtils.tips(this, Constants.COPYRIGHT,object : IConfirmClickCallBack {
                    override fun onClick() {
                    }
                })
            }
            //退出登录
            R.id.tvLogout->{


            }
        }
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

}
