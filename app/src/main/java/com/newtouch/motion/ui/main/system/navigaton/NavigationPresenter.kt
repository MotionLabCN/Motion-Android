package com.newtouch.motion.ui.main.system.navigaton

import com.common.baselibrary.base.BasePresenter
import com.newtouch.motion.entity.NavigationEntity
import com.newtouch.motion.http.HttpDefaultObserver
import com.newtouch.motion.http.RetrofitHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


/**
 * des 导航
 */
class NavigationPresenter (view: NavigationContract.View): BasePresenter<NavigationContract.View>(view)
    , NavigationContract.Presenter<NavigationContract.View> {

    override fun loadData() {
        RetrofitHelper.getApiService()
            .getNavigation()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : HttpDefaultObserver<MutableList<NavigationEntity>>() {
                override fun disposable(d: Disposable) {
                    addSubscribe(d)
                }

                override fun onSuccess(t: MutableList<NavigationEntity>) {
                    view?.showList(t)
                }

                override fun onError(errorMsg: String) {
                    view?.onError(errorMsg)
                }

            })
    }
}