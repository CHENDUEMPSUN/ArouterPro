package com.example.lib_base.mvvm.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


import com.example.lib_base.MyApplication;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by huangzhixiong on 16/10/8.
 * android 6.0+ 可以用这个
 * <style name="statusBarStyle" parent="@android:style/Theme.DeviceDefault.Light">
 * <item name="android:statusBarColor">@color/status_bar_color</item>
 * <item name="android:windowLightStatusBar">false</item>
 * </style>
 * <p>
 * <ui>
 * <li>获取手机型号 android.os.Build.MODEL;</li>
 * <li>获取手机版本 android.os.Build.VERSION.RELEASE;</li>
 * </ui>
 * <p>
 *
 * @author 蜗里沙
 */
public class AndroidHelper {
    static final String TAG = AndroidHelper.class.getSimpleName();


    public static void enableComponent(ComponentName componentName) {
        enableComponent(componentName,PackageManager.COMPONENT_ENABLED_STATE_ENABLED);
    }

    public static void disableComponent(ComponentName componentName) {
        enableComponent(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED);
    }

    public static void enableComponent(ComponentName componentName,int newState) {
        MyApplication.getInstance()
                .getApplicationContext()
                .getPackageManager()
                .setComponentEnabledSetting(componentName,
                    newState,
                    PackageManager.DONT_KILL_APP);
    }


    public static String getPackageName() {
        return MyApplication.getInstance().getPackageName();
    }


    /**
     * 是否有sd卡
     *
     * @return
     */
    public static boolean hasSdcard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 判断是否后台运行
     *
     * @param context
     * @return
     */
    public static boolean isRunningPackName(Context context) {
        String packageName = context.getPackageName();
        if (getRunningPackName(context).equals(packageName)) {
            return true;
        }
        return false;
    }

    /**
     * 检测手机是否安装某个应用
     */
    public static boolean checkPackage( String packageName) {
        //获取手机中已安装的应用程序
        List<ApplicationInfo> mAppList = MyApplication.getInstance().getPackageManager().getInstalledApplications(0);
        for (ApplicationInfo appInfo : mAppList) {
            if (appInfo.packageName.equalsIgnoreCase( packageName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isWeixinAvilible() {
        return checkPackage("com.tencent.mm");
    }

    public static String getRunningPackName(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        /** 获取当前正在运行的任务栈列表， 越是靠近当前运行的任务栈会被排在第一位，之后的以此类推 */
        List<ActivityManager.RunningTaskInfo> runningTasks = manager.getRunningTasks(1);

        /** 获得当前最顶端的任务栈，即前台任务栈 */
        ActivityManager.RunningTaskInfo runningTaskInfo = runningTasks.get(0);

        /** 获取前台任务栈的最顶端 Activity */
        ComponentName topActivity = runningTaskInfo.baseActivity;

        /** 获取应用的包名 */
        String packageName = topActivity.getPackageName();
        return packageName;
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
     * 关闭软键盘
     */
    public static void closeKey(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (activity.getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /***
     * 获取当前正在运行的进程
     *
     * @param context
     * @return
     */
    public static List<String> getRunningProcess(Context context) {
        List<String> mAppNameList = new ArrayList<String>(10);
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //获取正在运行的应用
        List<ActivityManager.RunningAppProcessInfo> run = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo ra : run) {
            //这里主要是过滤系统的应用和电话应用，当然你也可以把它注释掉。
            if (ra.processName.equals("system") || ra.processName.equals("com.android.phone")) {
                continue;
            }
            mAppNameList.add(ra.processName);
        }
        return mAppNameList;
    }



    // -----------------------------[1.3.4]---------------------


    public static final String samsung ="samsung";
    public static final String Xiaomi = "Xiaomi";
    public static final String Meizu = "Meizu";

    public static boolean isSamsung() {
        return samsung.equalsIgnoreCase(android.os.Build.MANUFACTURER);
    }

    public static boolean isXiaomi() {
        return Xiaomi.equalsIgnoreCase(android.os.Build.MANUFACTURER);
    }

    public static boolean isMeizu() {
        return Meizu.equalsIgnoreCase(android.os.Build.MANUFACTURER);
    }


    /**
     * 设置小米状态栏
     */
    public static boolean setMiuiStatusBarDarkMode(Activity activity, boolean darkmode) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 设置魅族状态栏
     */
    public static boolean setMeizuStatusBarDarkIcon(Activity activity, boolean dark) {
        boolean result = false;
        if (activity != null) {
            try {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                activity.getWindow().setAttributes(lp);
                result = true;
            } catch (Exception e) {
            }
        }
        return result;
    }


    public static String getVersionName() {
        PackageManager pm = MyApplication.getInstance().getPackageManager();
        PackageInfo pi;
        try {
            pi = pm.getPackageInfo(BuildConfig.APPLICATION_ID, 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "1.0.0";
        }
    }
}
