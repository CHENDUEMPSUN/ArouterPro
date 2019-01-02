package com.example.lib_base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.lib_base.utils.RouteUtils;

@Interceptor(priority = 8, name = "测试用拦截器")
public class MyIInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        Bundle bundle = postcard.getExtras();
        boolean b = bundle.getBoolean("isIntercept");
        if (!b) {
            callback.onContinue(postcard);
        } else {
            Log.d("MyIInterceptor", "onInterrupt");
            callback.onInterrupt(null);
            ARouter.getInstance().build(RouteUtils.Test)
                    .withString("value", "anyi").navigation();
        }
    }

    @Override
    public void init(Context context) {

    }
}
