package com.example.lib_base.mvvm.base;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.lib_base.MyApplication;
import com.example.lib_base.R;
import com.example.lib_base.mvvm.utils.AndroidHelper;
import com.example.lib_base.mvvm.utils.AppManager;
import com.example.lib_base.mvvm.utils.Method;
import com.example.lib_base.mvvm.utils.SharedStore;
import com.umeng.analytics.MobclickAgent;


import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


public abstract class BaseActivity extends AppCompatActivity implements OnClickListener {

    private static final String TAG = BaseActivity.class.getSimpleName();


    protected static final int REQUEST_LOGIN = 0x601;
    protected static final int REQUEST_CODE = 0x602;

    public static final int BASE_REQUEST_CHECK_PER = 51;

    /**
     * 保存上一次点击时间
     */
    private SparseArray<Long> lastClickTimes;

    boolean isActive = true;// activity是否活动

    protected Context mContext;
    protected BaseActivity mActivity;
    /**
     * 上一次退到后台时间
     */
    private long lastActive = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //* 修改状态栏颜色
        if (AndroidHelper.isXiaomi()) {
            AndroidHelper.setMiuiStatusBarDarkMode(this, true);
        }

        mContext = this;
        mActivity = this;

        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
        lastClickTimes = new SparseArray<>();

        if (savedInstanceState != null) {
            /**
             * Restore shared data store
             */
            MyApplication application = (MyApplication) getApplication();
            application.restoreSharedData(savedInstanceState);
        } else {
            SharedStore.getInstance().init();
        }

        Log.w("BaseActivity", getClass().getName());

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        MobclickAgent.onResume(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!Method.isRunningPackName(this) || !Method.isScreenOn(this)) {
            isActive = false;
            lastActive = System.currentTimeMillis();
        }
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        // 结束Activity&从堆栈中移除
        AppManager.getAppManager().removeActivity(this);

        lastClickTimes = null;
        super.onDestroy();
    }


    /**
     * 检查是否有权限
     * 调用了这个方法，请不要复写 onRequestPermissionsResult 方法
     *
     * @param permissionDesc 申请获取权限的描述,如：获取相机权限是为了拍照
     * @param permissions    The name of the permission being checked.
     * @see Manifest.permission
     * @see #onPermissionGranted(String)
     * @see #onPermissionDenied(String)
     */
    protected void checkPermission(String permissionDesc, final String... permissions) {
        for (String permisson : permissions) {
            if (ActivityCompat.checkSelfPermission(this, permisson)
                    == PackageManager.PERMISSION_GRANTED) {
                onPermissionGranted(permisson);
            } else {
                requestPermission(permissionDesc, permisson);
            }
        }
    }

    private void requestPermission(String permissionDesc, final String... permissions) {
        if (permissions == null || permissions.length < 1) {
            return;
        }

        if (!TextUtils.isEmpty(permissionDesc) && ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setMessage(permissionDesc)
                    .setCancelable(false)
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            onPermissionDenied(permissions[0]);
                            dialog.dismiss();
                        }
                    })
                    .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(mActivity, permissions, BASE_REQUEST_CHECK_PER);
                            dialog.dismiss();
                        }
                    });

            builder.show();
        } else {
            ActivityCompat.requestPermissions(this, permissions, BASE_REQUEST_CHECK_PER);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (permissions.length < 1 || requestCode != BASE_REQUEST_CHECK_PER)
            return;

        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                onPermissionGranted(permissions[i]);
            } else {
                onPermissionDenied(permissions[i]);
            }
        }
    }

    /**
     * 当你调用 checkPermission 传入了多个权限时，这个方法会调用多次
     *
     * @param grantedPermission g
     */
    protected void onPermissionGranted(String grantedPermission) {
        if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(grantedPermission)) {
        }
    }

    /**
     * 这个方法当你传入了多个权限时，会调用多次
     *
     * @param deniedPermission
     */
    protected void onPermissionDenied(String deniedPermission) {
        if (WRITE_EXTERNAL_STORAGE.equals(deniedPermission)) {
            AppManager.getAppManager().AppExit(mContext);
        }
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


    protected <T extends View> T $(@IdRes int id) {
        return (T) super.findViewById(id);
    }


    /**
     * 添加友盟自定义事件
     */
    @Override
    public void onClick(View v) {
        if (null != v.getTag()) {
            MobclickAgent.onEvent(BaseActivity.this, v.getTag().toString());
        }
    }

    public void onBackClick(View view) {
        onBackPressed();
    }

    public MyApplication getMyApp() {
        return MyApplication.getInstance();
    }

    public boolean isCompatDestroyed() {
        if (isFinishing()) return true;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (isDestroyed()) return true;
        }

        return false;

    }

}
