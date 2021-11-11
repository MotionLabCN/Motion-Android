package com.newtouch.motion.ui.login

import com.common.baselibrary.utils.PrefUtils
import com.common.baselibrary.base.BasePresenter
import com.common.baselibrary.utils.LogUtils
import com.newtouch.motion.constants.Constants
import com.newtouch.motion.entity.ToLoginEntity
import com.newtouch.motion.entity.UserEntity
import com.newtouch.motion.entity.VerifiCodeEntity
import com.newtouch.motion.http.HttpDefaultObserver
import com.newtouch.motion.event.LoginEvent
import com.newtouch.motion.http.RetrofitHelper
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus

class LoginPresenter(view: LoginContract.View) :
    BasePresenter<LoginContract.View>(view),
    LoginContract.Presenter<LoginContract.View> {


    override fun login(username: String, password: String) {
        RetrofitHelper.getApiService()
            .login(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : HttpDefaultObserver<UserEntity>() {
                override fun onSuccess(t: UserEntity) {
                    //登陆成功保存用户信息，并发送消息
                    PrefUtils.setObject(Constants.USER_INFO, t)
                    EventBus.getDefault().post(LoginEvent())
                    view?.loginSuccess()
                }

                override fun onError(errorMsg: String) {
                    view?.onError(errorMsg)
                }

                override fun disposable(d: Disposable) {
                    addSubscribe(d)
                }
            })
    }

    override fun getCode(phone: String) {
        RetrofitHelper.getApiService()
            .getCode(phone)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : HttpDefaultObserver<VerifiCodeEntity>() {
                override fun onSuccess(t: VerifiCodeEntity) {
//                    view?.loginSuccess()
                }

                override fun onError(errorMsg: String) {
//                    view?.onError(errorMsg)
                }

                override fun disposable(d: Disposable) {
                    addSubscribe(d)
                }
            })
    }

    override fun toLogin(phone: String, code: String) {
        RetrofitHelper.getApiService()
            .toLogin(smsCode = code, mobile = phone)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ToLoginEntity?> {
                override fun onSubscribe(d: Disposable) {
                    addSubscribe(d)
                }
                override fun onNext(data: ToLoginEntity) {
                    view?.loginSuccess()
                    PrefUtils.setString(Constants.ACCESS_TOKEN, data.access_token)
                    LogUtils.d("==========onSuccess=====" + data.access_token)
                }

                override fun onError(e: Throwable) {
                    LogUtils.d("==========onError=====" + e.toString())
                }

                override fun onComplete() {
                    LogUtils.d("==========onComplete=====")
                }
            })
    }


}
