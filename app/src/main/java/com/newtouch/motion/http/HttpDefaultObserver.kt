package com.newtouch.motion.http

import android.net.ParseException
import com.common.baselibrary.http.BusinessHttpException
import com.common.baselibrary.utils.LogUtils
import com.common.baselibrary.utils.ToastUtils
import com.google.gson.JsonParseException
import com.newtouch.motion.utils.AppManager
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.json.JSONException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.lang.reflect.ParameterizedType
import org.greenrobot.eventbus.EventBus

import com.newtouch.motion.ui.login.LoginActivity
import java.lang.Exception
import javax.net.ssl.SSLHandshakeException
import kotlin.math.log


/**
 * des 给Response脱壳,对服务器错误统一处理
 *
 */
abstract class HttpDefaultObserver<T> :Observer<BaseResponse<T>> {

    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
        disposable(d)
    }

//    override fun onNext(t: BaseResponse<T>) {
//        if (t.errorCode==0) {
//
//            if (t.data==null){
//                val tClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>
//                t.data = tClass.newInstance()
//            }
//            t.data?.let { onSuccess(it) }
//        }
//        //code!=0代表业务出错，进行过滤
//        else{
//            filterCode(t.errorMsg,t.errorCode)
//        }
//
//    }

    override fun onNext(data: BaseResponse<T>) {

        if (data.code==200) {
            if (data.data==null){
                val tClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>
                data.data = tClass.newInstance()
            }
            data.data?.let {
                onSuccess(it) }
        } else{
//            filterCode(t.message,t.code)
            data.message?.let { filterCode(it,data.code) }
        }

    }


    override fun onError(e: Throwable) {
        var errorMsg = if (e is UnknownHostException) {
            "网络异常"
        } else if (e is JSONException || e is JsonParseException||e is ParseException) {
            "数据异常"
        } else if (e is SocketTimeoutException) {
            "连接超时"
        } else if (e is ConnectException) {
            "连接错误"
        } else if (e is BusinessHttpException){
            e.businessMessage
        } else if (e is SSLHandshakeException){
            "证书验证失败"
        }else{
            "未知错误"
        }

        if (errorMsg.isNotEmpty()){
            ToastUtils.show(errorMsg)
            LogUtils.e("====errorMsg=>>>>====$e")
        }

        onError(errorMsg)
    }

    private fun filterCode(msg: String, code: Int) {
        when (code) {
            //登录失败
            -1001 -> {
                AppManager.resetUser()
                onError(BusinessHttpException(msg, code))
            }
            //未知code,将errorMsg封装成异常,由onError()处理
            else -> onError(BusinessHttpException(msg, code))
        }
    }

    abstract fun disposable(d: Disposable)
    abstract fun onSuccess(t:T)
    abstract fun onError(errorMsg:String)

}