package com.example.module_chat;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.lib_base.databinding.ActivityTestBinding;
import com.example.lib_base.utils.RouteUtils;

@Route(path = RouteUtils.Test)
public class TestActivity extends AppCompatActivity {

    @Autowired
    String value;

    ActivityTestBinding bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_test);
        ARouter.getInstance().inject(this);
        bind.setData(value);
    }
}
