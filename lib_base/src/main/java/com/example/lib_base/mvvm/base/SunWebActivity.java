package com.example.lib_base.mvvm.base;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.lib_base.R;
import com.example.lib_base.databinding.ActivityWebviewBinding;
import com.example.lib_base.mvvm.utils.ScreenHelper;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;


public class SunWebActivity extends BaseActivity {

    public static final String TAG = SunWebActivity.class.getSimpleName();

    public static final String DOCTOR_URL = "doctor_url";
    public static final String DOCTOR_DATA = "doctor_data";
    public static final String DOCTOR_TITLE = "doctor_title";

    public static final String WEB_LOAD_TYPE = "web_load_type";

    int mLoadType;
    private boolean appShareAfter = false;

    public interface WebLoadType {
        int DATA = 0x10001;
        int URL = 0x10002;
    }

    ActivityWebviewBinding mBinding;


    public static void start(Context context, String url) {
        Intent intent = new Intent(context, SunWebActivity.class);
        intent.putExtra(DOCTOR_URL, url);
        intent.putExtra(WEB_LOAD_TYPE, WebLoadType.URL);
        context.startActivity(intent);
    }

    public static void start(Context context, String title, String data) {
        Intent intent = new Intent(context, SunWebActivity.class);
        intent.putExtra(DOCTOR_TITLE, title);
        intent.putExtra(DOCTOR_DATA, data);
        intent.putExtra(WEB_LOAD_TYPE, WebLoadType.DATA);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVariables();

        initWebView();

    }

    void initVariables() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_webview);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        String tempTitle = getString(R.string.loading);
        Intent intent = getIntent();
        mLoadType = intent.getIntExtra(WEB_LOAD_TYPE, 0);
        if (mLoadType == WebLoadType.URL) {
            String url = intent.getStringExtra(DOCTOR_URL);
            if (url.contains("hiddenTopBar=")) {
                String[] split = url.split("hiddenTopBar=");
                if (split[1].equals("Y")) {
                    mBinding.setVisible("visible");
                }
            } else {
                mBinding.setVisible(null);
            }
            mBinding.webview.loadUrl(url);
        } else if (mLoadType == WebLoadType.DATA) {
            float iScale = 100 * ScreenHelper.getScreentDis(this);
            mBinding.webview.setInitialScale((int) iScale);

            tempTitle = intent.getStringExtra(DOCTOR_TITLE);
            String data = intent.getStringExtra(DOCTOR_DATA);
            mBinding.webview.loadDataWithBaseURL(null, data, "text/html", "UTF-8", null);
        }

    }

    void initWebView() {
        mBinding.webview.addJavascriptInterface(this, "DoctorJsInterface");

        mBinding.webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                /**
                 * 这里注意了  需要根据登入与否传TOKEN
                 */
//                if (DoctorApplication.getInstance().isLogined()) {
//                    String jsUrl = "javascript:checkUserMsg(\"" + DoctorApplication.getInstance().getToken() + "\")";
//                    mBinding.webview.loadUrl(jsUrl);
//                }

                if (mLoadType == WebLoadType.DATA) return;

                String title = webView.getTitle();
                if (!TextUtils.isEmpty(title)) {
                    // mToolbar.setTitle(title);
                }
            }
        });

    }

}