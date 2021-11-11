package com.common.baselibrary.utils;

import android.widget.Toast;

import com.common.baselibrary.BaseApplication;

/**
 * des toast工具类
 */
public class ToastUtils {

    public static void show(String msg){
        Toast.makeText(BaseApplication.Companion.getContext(),msg,Toast.LENGTH_SHORT).show();
    }
}
