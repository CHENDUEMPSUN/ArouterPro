package com.example.module_chat;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.lib_base.Test;
import com.example.lib_base.databinding.ActivityLoginBinding;
import com.example.lib_base.utils.RouteUtils;

@Route(path = RouteUtils.Me_Login)
public class LoginActivity extends AppCompatActivity {

    @Autowired
    public int age;
    @Autowired
    public String name;
    @Autowired
    public Test obj;

    ActivityLoginBinding bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_login);
        ARouter.getInstance().inject(this);
        bind.setName(name);
        bind.setAge(age + "");
        bind.setTest(obj);
    }
}
