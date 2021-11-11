package com.newtouch.motion.ui.main.tab

import com.common.baselibrary.base.BasePresenter
import com.newtouch.motion.constants.Constants
import com.newtouch.motion.entity.TabEntity
import com.newtouch.motion.http.HttpDefaultObserver
import com.newtouch.motion.http.RetrofitHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class TabPresenter(view:TabContract.View):BasePresenter<TabContract.View>(view)
    , TabContract.Presenter<TabContract.View>{

    override fun loadData(type:Int) {
        when(type){
            //项目
            Constants.PROJECT_TYPE->{
                RetrofitHelper.getApiService()
                    .getProjectTabList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object :HttpDefaultObserver<MutableList<TabEntity>>(){
                        override fun disposable(d: Disposable) {
                            addSubscribe(d)
                        }

                        override fun onSuccess(t: MutableList<TabEntity>) {
                            view?.showList(t)
                        }

                        override fun onError(errorMsg: String) {
                            view?.onError(errorMsg)
                        }
                    })
            }
            //公众号
            Constants.ACCOUNT_TYPE->{
                RetrofitHelper.getApiService()
                    .getAccountTabList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object :HttpDefaultObserver<MutableList<TabEntity>>(){
                        override fun disposable(d: Disposable) {
                            addSubscribe(d)
                        }

                        override fun onSuccess(t: MutableList<TabEntity>) {
                            view?.showList(t)
                        }

                        override fun onError(errorMsg: String) {
                            view?.onError(errorMsg)
                        }
                    })
            }
        }

    }

}