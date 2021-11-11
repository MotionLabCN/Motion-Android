package com.newtouch.motion.ui.login

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.common.baselibrary.utils.LogUtils
import com.common.baselibrary.utils.ScreenUtils
import com.common.baselibrary.utils.ToastUtils
import com.common.baselibrary.utils.UIUtils.context
import com.newtouch.motion.R
import com.newtouch.motion.base.AppBaseActivity
import com.newtouch.motion.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login1.*





class LoginActivity1 : AppBaseActivity<LoginContract.Presenter<LoginContract.View>>(),
    LoginContract.View, View.OnClickListener {

    override fun getLayoutId(): Int {
        return R.layout.activity_login1
    }

    override fun createPresenter(): LoginContract.Presenter<LoginContract.View>? {
        return LoginPresenter(this)
    }

    override fun getContext(): Context? {
        return this
    }

    override fun init(savedInstanceState: Bundle?) {
        bt_login.setOnClickListener(this)
        iv_deal.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.bt_login -> {
                showDialog()

            }
//            R.id.tvRegister -> intent(RegisterActivity::class.java, false)
//            R.id.tvSkip -> finish()
//            R.id.ivClear -> {
//
//            }
            R.id.iv_deal -> {

            }
        }
    }

    /**
     * 登录时给具备点击事件的View上锁，登陆失败时解锁
     * 并且施加动画
     */
    private fun setViewStatus(lockStatus: Boolean) {

    }


    override fun loginSuccess() {
//        PrefUtils.setBoolean(Constants.LOGIN, true)
        finish()
    }

    override fun onError(error: String) {

        ToastUtils.show(error)
    }


    private var codeDialog: Dialog? = null

    private fun showDialog() {
        if (null != codeDialog && codeDialog!!.isShowing){
            codeDialog!!.dismiss()
        }
        codeDialog = Dialog(this, R.style.DialogTheme)
        val view = View.inflate(context, R.layout.dialog_bottom_login, null)
        val mLoginPhone = view.findViewById<TextView>(R.id.tv_login_for_phone)
        val mLoginGithub = view.findViewById<TextView>(R.id.tv_login_for_github)
        val mLoginApple = view.findViewById<TextView>(R.id.tv_login_for_apple)
        val mLoginOther = view.findViewById<TextView>(R.id.tv_login_for_other)
        mLoginPhone.setOnClickListener{
            ToastUtils.show("手机登陆")
            startActivity(Intent(context, MainActivity::class.java))
            codeDialog?.dismiss()

        }
        mLoginGithub.setOnClickListener{
            ToastUtils.show("github登陆")
            codeDialog?.dismiss()

        }
        mLoginApple.setOnClickListener{
            ToastUtils.show("apple登陆")
            codeDialog?.dismiss()

        }
        mLoginOther.setOnClickListener{
            startActivity(Intent(context, LoginForOtherActivity::class.java))
            ToastUtils.show("其他登陆")
            codeDialog?.dismiss()
        }

        codeDialog?.setContentView(view)
        val window = codeDialog?.window
        window!!.setGravity(Gravity.BOTTOM)
        window.setWindowAnimations(R.style.main_menu_animStyle)
        window.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        codeDialog?.show()

    }

}
