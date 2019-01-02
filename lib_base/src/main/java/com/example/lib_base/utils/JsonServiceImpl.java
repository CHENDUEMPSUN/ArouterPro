package com.example.lib_base.utils;

import android.content.Context;

import com.alibaba.android.arouter.facade.service.SerializationService;

import java.lang.reflect.Type;

public class JsonServiceImpl implements SerializationService {
    @Override
    public <T> T json2Object(String input, Class<T> clazz) {
        return null;
    }

    @Override
    public String object2Json(Object instance) {
        return null;
    }

    @Override
    public <T> T parseObject(String input, Type clazz) {
        return null;
    }

    @Override
    public void init(Context context) {

    }
}
