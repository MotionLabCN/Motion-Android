package com.newtouch.motion.http;

import com.common.baselibrary.utils.PrefUtils;
import com.newtouch.motion.constants.Constants;

import java.io.IOException;
import java.util.HashSet;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * des cookie持久化保存
 */
public class SaveCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
//        if (!AppUtils.isLogin()) {
            //set-cookie可能为多个
            if (!response.headers("set-cookie").isEmpty()) {
                HashSet<String> cookies = new HashSet<>(response.headers("set-cookie"));
                PrefUtils.INSTANCE.setHashSet(Constants.COOKIE, cookies);
            }
//        }
        return response;
    }
}