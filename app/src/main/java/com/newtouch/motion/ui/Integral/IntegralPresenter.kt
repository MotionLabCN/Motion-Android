package com.newtouch.motion.ui.Integral

import com.common.baselibrary.base.BasePresenter
import com.newtouch.motion.entity.IntegralRecordEntity
import com.newtouch.motion.http.HttpDefaultObserver
import com.newtouch.motion.http.RetrofitHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class IntegralPresenter(view:IntegralContract.View): BasePresenter<IntegralContract.View>(view)
,IntegralContract.Presenter<IntegralContract.View>{

    override fun loadData(pageNum:Int) {
        RetrofitHelper.getApiService()
            .getIntegralRecord(pageNum)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : HttpDefaultObserver<IntegralRecordEntity>(){
                override fun disposable(d: Disposable) {
                    addSubscribe(d)
                }

                override fun onSuccess(t: IntegralRecordEntity) {
                    view?.showList(t)
                }

                override fun onError(errorMsg: String) {
                    view?.onError(errorMsg)
                }
            })
    }
}