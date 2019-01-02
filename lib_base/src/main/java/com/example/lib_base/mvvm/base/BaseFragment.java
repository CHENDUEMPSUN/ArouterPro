package com.example.lib_base.mvvm.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

import com.example.lib_base.mvvm.utils.AndroidHelper;
import com.example.lib_base.mvvm.utils.FontHelper;
import com.example.lib_base.mvvm.utils.ToastUtil;
import com.umeng.analytics.MobclickAgent;

import java.io.Serializable;

public class BaseFragment extends Fragment  {
    private static final String TAG = "BaseFragment";
    protected static final String ARG_PARAM = AndroidHelper.getPackageName() + "arg_param1";

    public static <T extends BaseFragment> T getInstance(Class<T> clz,Object... objs) {
        Bundle args = new Bundle();
        for (int i = 0; i < objs.length; i++) {
            if (objs[i] instanceof String){
                args.putString(ARG_PARAM+i, (String) objs[i]);
            } else if (objs[i] instanceof Double){
                args.putDouble(ARG_PARAM+i, (Double)objs[i]);
            } else if ( objs[i] instanceof Serializable ){
                args.putSerializable(ARG_PARAM+i, (Serializable)objs[i]);
            } else if( objs[i] instanceof Parcelable){
                args.putParcelable(ARG_PARAM + i,(Parcelable)objs[i]);
            }
        }

        return getInstance(clz,args);
    }

    public static <T extends BaseFragment> T getInstance(Class<T> clz,Bundle args) {
        try {
            T t = clz.newInstance();
            t.setArguments(args);
            return t;
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return (T)new BaseFragment();
    }


    /**
     * 保存上一次点击时间
     */
    private SparseArray<Long> lastClickTimes;

    protected Activity mActivity;
    protected Context mContext;
    protected Fragment mFragment;

    protected View mRootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mFragment = this;
        mActivity = getActivity();
        Log.d(TAG, "onCreate: "+getActivity());
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lastClickTimes = new SparseArray<Long>();
        FontHelper.applyFont(getActivity(), getView());

    }


    @Override
    public void onDestroy() {
        lastClickTimes = null;
        super.onDestroy();
    }

    /**
     * 检查是否可执行点击操作 防重复点击
     *
     * @return 返回true则可执行
     */
    public boolean checkClick(int id) {
        Long lastTime = lastClickTimes.get(id);
        Long thisTime = System.currentTimeMillis();
        lastClickTimes.put(id, thisTime);
        if (lastTime != null && thisTime - lastTime < 800) {
            // 快速双击，第二次不处理
            return false;
        } else {
            return true;
        }
    }


//    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";


//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
//    }

    public void showToast(CharSequence text) {
        ToastUtil.getInstant().show(mActivity, text);
    }

    public void showToast(@StringRes int resId) {
        ToastUtil.getInstant().show(mActivity, resId);
    }


    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(getClass().getSimpleName()); //统计页面，"MainScreen"为页面名称，可自定义
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(getClass().getSimpleName());
    }


}
