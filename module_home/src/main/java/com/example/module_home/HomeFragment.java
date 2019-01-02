package com.example.module_home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.lib_base.Test;
import com.example.lib_base.databinding.FragmentHomeBinding;
import com.example.lib_base.utils.RouteUtils;

@Route(path = RouteUtils.Home_Fragment_Main)
public class HomeFragment extends Fragment {
    FragmentHomeBinding bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return bind.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bind.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance()
                        .build(RouteUtils.Me_Login)
                        .withString("name", "888")
                        .withInt("age", 11)
                        .withSerializable("obj", new Test("sl", 27))
                        .greenChannel()
                        .navigation();
            }
        });
    }
}
