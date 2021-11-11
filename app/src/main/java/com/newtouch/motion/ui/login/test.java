package com.newtouch.motion.ui.login;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.common.baselibrary.base.BaseActivity;
import com.common.baselibrary.base.IBasePresenter;
import com.newtouch.motion.http.RetrofitHelper;

import androidx.annotation.Nullable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Dan on 2021/10/20.
 */

class test extends BaseActivity {


    @SuppressLint("CheckResult")
    @Override
    protected void init(@Nullable Bundle savedInstanceState) {


        RetrofitHelper.getApiService()
                .toLogin("","","","","","")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(toLoginEntity -> {

                });
    }

    @Nullable
    @Override
    protected IBasePresenter<?> createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
