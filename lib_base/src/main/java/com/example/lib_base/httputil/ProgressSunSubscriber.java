package com.example.lib_base.httputil;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.subsciber.IProgressDialog;
import com.zhouyou.http.subsciber.ProgressCancelListener;


public abstract class ProgressSunSubscriber<T> extends SunSubscriber<T> implements ProgressCancelListener {
    private IProgressDialog progressDialog;
    private Dialog mDialog;
    private boolean isShowProgress = true;


    /**
     * 默认不显示弹出框，不可以取消
     *
     * @param context  上下文
     */
    public ProgressSunSubscriber(Context context) {
        super(context);
        init(false);
    }

    /**
     * 自定义加载进度框
     *
     * @param context 上下文
     * @param progressDialog 自定义对话框
     */
    public ProgressSunSubscriber(Context context, IProgressDialog progressDialog) {
        super(context);
        this.progressDialog = progressDialog;
        init(false);
    }

    /**
     * 自定义加载进度框,可以设置是否显示弹出框，是否可以取消
     *
     * @param context 上下文
     * @param progressDialog 对话框
     * @param isShowProgress  是否显示对话框
     * @param isCancel  对话框是否可以取消
     */
    public ProgressSunSubscriber(Context context, IProgressDialog progressDialog, boolean isShowProgress, boolean isCancel) {
        super(context);
        this.progressDialog = progressDialog;
        this.isShowProgress = isShowProgress;
        init(isCancel);
    }

    /**
     * 初始化
     *
     * @param isCancel 对话框是否可以取消
     */
    private void init(boolean isCancel) {
        if (progressDialog == null) return;
        mDialog = progressDialog.getDialog();
        if (mDialog == null) return;
        mDialog.setCancelable(isCancel);
        if (isCancel) {
            mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    ProgressSunSubscriber.this.onCancelProgress();
                }
            });
        }
    }

    /**
     * 展示进度框
     */
    private void showProgress() {
        if (!isShowProgress) {
            return;
        }
        if (mDialog != null) {
            if (!mDialog.isShowing()) {
                mDialog.show();
            }
        }
    }

    /**
     * 取消进度框
     */
    private void dismissProgress() {
        if (!isShowProgress) {
            return;
        }
        if (mDialog != null) {
            if (mDialog.isShowing()) {
                mDialog.dismiss();
            }
        }
    }

    @Override
    public void onStart() {
        showProgress();
    }

    @Override
    public void onComplete() {
        dismissProgress();
    }

    @Override
    public void onError(ApiException e) {
        dismissProgress();
        //int errCode = e.getCode();
        /*if (errCode == ApiException.ERROR.TIMEOUT_ERROR) {
            ToastUtil.showToast(contextWeakReference.get(), "网络中断，请检查您的网络状态");
        } else if (errCode == ApiException.ERROR.NETWORD_ERROR) {
            ToastUtil.showToast(contextWeakReference.get(), "请检查您的网络状态");
        } else {
            ToastUtil.showToast(contextWeakReference.get(), "error:" + e.getMessage());
        }*/
    }

    @Override
    public void onCancelProgress() {
        if (!isDisposed()) {
            dispose();
        }
    }
}
