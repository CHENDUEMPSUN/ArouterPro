package com.example.lib_base.httputil;

import com.google.gson.Gson;


public class GsonUtil {
    static Gson gson;
    public static <T> T fromJson(String js, Class<T> type){
        if (gson==null)
            gson=new Gson();
        return   gson.fromJson(js,type);
    }
}
