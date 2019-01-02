package com.example.lib_base.mvvm.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


import com.example.lib_base.MyApplication;

import java.util.Timer;
import java.util.TimerTask;

public class ScreenHelper {

    static final String TAG = ScreenHelper.class.getSimpleName();

    public static void printScreenInfo(Context context) {
        Log.w(TAG, "getScreenHeight:" + getScreenHeight(context) +
                "\ngetScreenWidth:" + getScreenWidth(context) +
                "\ngetScreentDisDpi:" + getScreentDisDpi(context) +
                "\ngetScreentDis:" + getScreentDis(context));

        Log.w(TAG, "android.os.Build.MANUFACTURER:" + Build.MANUFACTURER +
                "\nandroid.os.Build.MODEL:" + Build.MODEL);

        getStatusBarHeight(context);
    }

    public static void printViewInfo(final View view){
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                int height = view.getHeight();
                int measuredHeight = view.getMeasuredHeight();
                int dpHeight = px2dip(height);
            }
        },200);
    }


    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    public static boolean isScreenOn(Context context) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = true;//如果为true，则表示屏幕“亮”了，否则屏幕“暗”了。
        if (Build.VERSION.SDK_INT < 20) {
            isScreenOn = pm.isScreenOn();
        } else {
            isScreenOn = pm.isInteractive();
        }
        return isScreenOn;
    }

    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
            Log.w(TAG,String.format("状态栏高度->\nres.getDimension():%f\nres.getDimensionPixelOffset():%d\nres.getDimensionPixelSize():%d",res.getDimension(resourceId),res.getDimensionPixelOffset(resourceId),res.getDimensionPixelSize(resourceId)));
        }
        return statusBarHeight;
    }

    /**
     * 获取屏幕宽度
     *
     * @param context context
     * @return widthPixels
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenWidth() {
        return MyApplication.getInstance().getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕宽度
     *
     * @param context context
     * @return heightPixels
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 屏幕密度,如1.5/2/2.5/3
     *
     * @param context context
     * @return density
     */
    public static float getScreentDis(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static float getScreentDisDpi(Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    /**
     * 关闭软键盘
     */
    public static void closeKey(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (activity.getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 打开软键盘
     */
    public static void openKey(Context context, final EditText inputView) {
        final InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                inputView.setFocusable(true);
                inputView.setFocusableInTouchMode(true);
                inputView.requestFocus();
                inputMethodManager.showSoftInput(inputView, InputMethodManager.SHOW_IMPLICIT);
            }
        }, 300);
    }

    /**
     * 根据手机的分辨率从dp的单位转成为px(像素)
     *
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从px(像素)的单位转成为dp
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int px2dip(float pxValue) {
        final float scale = MyApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

}
