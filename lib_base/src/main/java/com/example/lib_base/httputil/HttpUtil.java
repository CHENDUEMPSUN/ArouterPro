package com.example.lib_base.httputil;

import com.zhouyou.http.EasyHttp;

import io.reactivex.Observable;

public class HttpUtil {

    public static final String BASE_URL="http://120.76.205.151:1400/";

    private static HttpUtil httpUtil;

    public static HttpUtil getInstance() {
        if (httpUtil==null)
            httpUtil=new HttpUtil();
        return httpUtil;
    }

    public Observable getTop250(){
       return EasyHttp.get("http://t.yushu.im/v2/movie/top250?start=0&count=1").execute(String.class);
    }

}
