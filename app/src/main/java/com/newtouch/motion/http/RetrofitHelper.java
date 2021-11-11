package com.newtouch.motion.http;

/**
 * des 实例化Retrofit,获取ApiService
 *
 */
public class RetrofitHelper {
    private static ApiService apiService;
    private RetrofitHelper(){}
    public static ApiService getApiService(){
        return apiService;
    }
    static {
        apiService= RetrofitFactory.factory().create(ApiService.class);
    }


}
