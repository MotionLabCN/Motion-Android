package com.newtouch.motion.http;

import com.common.baselibrary.BaseApplication;
import com.common.baselibrary.http.HttpLoggingInterceptor;
import com.common.baselibrary.utils.LogUtils;
import com.common.baselibrary.utils.PrefUtils;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.newtouch.motion.BuildConfig;
import com.newtouch.motion.constants.ApiConstants;
import com.newtouch.motion.constants.Constants;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * des Retrofit工厂类
 */
class RetrofitFactory {
    private static String BASE_URL = "http://183.66.65.207:8081";  //http://192.168.0.224:8800/verification/code/motion?mobile=18062123500

    private static OkHttpClient.Builder getOkHttpClientBuilder() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkHttp");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        loggingInterceptor.setColorLevel(Level.INFO);
        File cacheFile = new File(BaseApplication.Companion.getContext().getCacheDir(), "cache");
        //100Mb
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);

        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(),
                        new SharedPrefsCookiePersistor(BaseApplication.Companion.getContext()));
        return new OkHttpClient.Builder()
                .readTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
//                .addInterceptor(new AddCookiesInterceptor())
//                .addInterceptor(new SaveCookiesInterceptor())
//                .addInterceptor(new HeaderInterceptor())
                .cookieJar(cookieJar)
                .cache(cache);
    }

    static Retrofit factory() {
        OkHttpClient okHttpClient = getOkHttpClientBuilder().build();
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .baseUrl(ApiConstants.BASE_URL)
                .baseUrl(BASE_URL)
                .build();
    }


    private static class HeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();
            Request builder = request.newBuilder()
                    .addHeader("Authorization", PrefUtils.INSTANCE.getString(Constants.ACCESS_TOKEN))
//                    .addHeader("version", BuildConfig.VERSION_NAME)
                    .build();

            LogUtils.INSTANCE.d("========Authorization==="+PrefUtils.INSTANCE.getString(Constants.ACCESS_TOKEN));
            Response response = chain.proceed(builder);
            MediaType mediaType = request.body().contentType();
            String content = response.body().string();
            return response.newBuilder()
                    .body(ResponseBody.create(mediaType, content))
                    .build();
        }
    }
}
