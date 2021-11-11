package com.newtouch.motion.ui.main.system.list

import com.common.baselibrary.base.BasePresenter
import com.newtouch.motion.entity.SystemListEntity
import com.newtouch.motion.http.HttpDefaultObserver
import com.newtouch.motion.http.RetrofitHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class SystemListPresenter(view:SystemListContract.View):BasePresenter<SystemListContract.View>(view)
    ,SystemListContract.Presenter<SystemListContract.View> {


    override fun loadData() {
        RetrofitHelper.getApiService()
            .getSystemList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :HttpDefaultObserver<MutableList<SystemListEntity>>(){
                override fun disposable(d: Disposable) {
                    addSubscribe(d)
                }

                override fun onSuccess(t: MutableList<SystemListEntity>) {
                    view?.showList(t)
                }

                override fun onError(errorMsg: String) {
                    view?.onError(errorMsg)
                }

            })
    }
}