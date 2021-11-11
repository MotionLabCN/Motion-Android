package com.newtouch.motion.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import com.common.baselibrary.utils.ToastUtils
import com.common.baselibrary.utils.UIUtils.context
import com.newtouch.motion.R
import com.newtouch.motion.base.AppBaseActivity
import com.newtouch.motion.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login_for_other2.*



class LoginForOtherActivity2 : AppBaseActivity<LoginContract.Presenter<LoginContract.View>>(),
    LoginContract.View, View.OnClickListener {

    private var mPhone :String =""

    override fun getLayoutId(): Int {
        return R.layout.activity_login_for_other2
    }

    override fun createPresenter(): LoginContract.Presenter<LoginContract.View>? {
        return LoginPresenter(this)
    }

    override fun getContext(): Context? {
        return this
    }

    override fun init(savedInstanceState: Bundle?) {
        bt_login.setOnClickListener(this)

         mPhone = intent.getStringExtra("phone")?:""
        if (!TextUtils.isEmpty(mPhone)){
            tv_phone_number.text = "请输入${mPhone}收到的短\\n信验证码"
        }

        bt_login.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {
                bt_login.isEnabled = char.toString().length == 6
            }

            override fun afterTextChanged(char: Editable?) {

            }

        })


    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.bt_login -> {
                presenter?.toLogin(mPhone, et_code.text.toString())
            }

            R.id.iv_deal -> {

            }
        }
    }


    override fun loginSuccess() {
//        PrefUtils.setBoolean(Constants.LOGIN, true)
        startActivity(Intent(context,MainActivity::class.java))
        finish()
    }

    override fun onError(error: String) {

        ToastUtils.show(error)
    }


}
