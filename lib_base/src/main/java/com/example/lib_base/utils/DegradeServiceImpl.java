package com.example.lib_base.utils;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.DegradeService;

@Route(path = "/xxx/xxx")
public class DegradeServiceImpl implements DegradeService {
    @Override
    public void onLost(Context context, Postcard postcard) {
        Log.d("DegradeServiceImpl", "出现异常了");
//        ARouter.getInstance()
//                .build(RouteUtils.Me_Login)
//                .withString("name", "888")
//                .withInt("age", 11)
//                .withSerializable("obj", new Test("sl", 27))
//                .greenChannel()
//                .navigation();

    }

    @Override
    public void init(Context context) {

    }
}
