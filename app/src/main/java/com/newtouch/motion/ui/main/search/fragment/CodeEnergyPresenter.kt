package com.newtouch.motion.ui.main.search.fragment

import com.common.baselibrary.base.BasePresenter
import com.newtouch.motion.entity.CodeEnergyEntity
import com.newtouch.motion.http.HttpDefaultObserver
import com.newtouch.motion.http.RetrofitHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class CodeEnergyPresenter(view: CodeEnergyContract.View) :
    BasePresenter<CodeEnergyContract.View>(view),
    CodeEnergyContract.Presenter<CodeEnergyContract.View> {


    override fun getCodeEnergyList(category: String, pageNum: Int) {
        RetrofitHelper.getApiService()
            .getCodeGoodsList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : HttpDefaultObserver<CodeEnergyEntity>() {
                override fun disposable(d: Disposable) {
                    addSubscribe(d)
                }

                override fun onSuccess(t: CodeEnergyEntity) {
                    view?.showCodeEnergyList(t)

                }

                override fun onError(errorMsg: String) {
                    view?.onError(errorMsg)

                }

            })
    }

}