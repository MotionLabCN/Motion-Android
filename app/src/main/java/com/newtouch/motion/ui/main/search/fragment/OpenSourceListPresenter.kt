package com.newtouch.motion.ui.main.search.fragment

import com.common.baselibrary.base.BasePresenter
import com.common.baselibrary.utils.LogUtils
import com.newtouch.motion.entity.HotCodeListEntity
import com.newtouch.motion.entity.OpenCodeCategoryEntity
import com.newtouch.motion.http.HttpDefaultObserver
import com.newtouch.motion.http.RetrofitHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class OpenSourceListPresenter(view: OpenSourceListContract.View) :
    BasePresenter<OpenSourceListContract.View>(view),
    OpenSourceListContract.Presenter<OpenSourceListContract.View> {


    override fun getHotCodeList(category: String, pageNum: Int) {
        RetrofitHelper.getApiService()
            .getHotCodeList("", "")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : HttpDefaultObserver<HotCodeListEntity>() {
                override fun disposable(d: Disposable) {
                    addSubscribe(d)
                }

                override fun onSuccess(t: HotCodeListEntity) {
                    view?.showHotCodeList(t)

                }

                override fun onError(errorMsg: String) {
                    view?.onError(errorMsg)

                }

            })
    }

    override fun getNewStartList(category: String, pageNum: Int) {
        RetrofitHelper.getApiService()
            .getNewStarList("", "")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : HttpDefaultObserver<HotCodeListEntity>() {
                override fun disposable(d: Disposable) {
                    addSubscribe(d)
                }

                override fun onSuccess(t: HotCodeListEntity) {
                    view?.showNewStartList(t)

                }

                override fun onError(errorMsg: String) {
                    view?.onError(errorMsg)

                }

            })

    }

    override fun getCategoryList() {
        RetrofitHelper.getApiService()
            .getCategoryList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : HttpDefaultObserver<MutableList<OpenCodeCategoryEntity>>() {
                override fun disposable(d: Disposable) {
                    addSubscribe(d)
                }

                override fun onSuccess(t: MutableList<OpenCodeCategoryEntity>) {
                    view?.showHotCodeList(t)

                }

                override fun onError(errorMsg: String) {
                    view?.onError(errorMsg)

                }

            })
    }
}