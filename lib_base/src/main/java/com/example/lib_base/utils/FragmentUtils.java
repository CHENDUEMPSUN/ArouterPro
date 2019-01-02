package com.example.lib_base.utils;

import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;

public class FragmentUtils {
    public static Fragment getHomeFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteUtils.Home_Fragment_Main).navigation();
        return fragment;
    }

    public static Fragment getChatFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteUtils.Chat_Fragment_Main).navigation();
        return fragment;
    }

    public static Fragment getRecomFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteUtils.Recom_Fragment_Main).navigation();
        return fragment;
    }

    public static Fragment getMeFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteUtils.Me_Fragment_Main).navigation();
        return fragment;
    }
    public static Fragment getTestFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteUtils.Test_Fragment_Main).navigation();
        return fragment;
    }
}
