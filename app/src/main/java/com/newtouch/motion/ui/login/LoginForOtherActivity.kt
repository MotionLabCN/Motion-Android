package com.newtouch.motion.ui.login

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import com.common.baselibrary.utils.ToastUtils
import com.common.baselibrary.utils.UIUtils.context
import com.newtouch.motion.R
import com.newtouch.motion.base.AppBaseActivity
import kotlinx.android.synthetic.main.activity_login_for_other.*


class LoginForOtherActivity : AppBaseActivity<LoginContract.Presenter<LoginContract.View>>(),
    LoginContract.View, View.OnClickListener {

    override fun getLayoutId(): Int {
        return R.layout.activity_login_for_other
    }

    override fun createPresenter(): LoginContract.Presenter<LoginContract.View>? {
        return LoginPresenter(this)
    }

    override fun getContext(): Context? {
        return this
    }

    override fun init(savedInstanceState: Bundle?) {
        bt_login.setOnClickListener(this)

        bt_login.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                bt_login.isEnabled = char.toString().length == 11
            }

            override fun afterTextChanged(char: Editable?) {

            }

        })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.bt_login -> {
                val mPhone = et_phone.text.toString()
                presenter?.getCode(mPhone)
//                startActivity(Intent(context,LoginForOtherActivity2::class.java))

                val intent = Intent()
                intent.setClass(context, LoginForOtherActivity2::class.java)
                intent.putExtra("phone", mPhone)
                startActivity(intent)
            }

            R.id.iv_deal -> {

            }
        }
    }


    override fun loginSuccess() {
//        PrefUtils.setBoolean(Constants.LOGIN, true)
        finish()
    }

    override fun onError(error: String) {

        ToastUtils.show(error)
    }


}
