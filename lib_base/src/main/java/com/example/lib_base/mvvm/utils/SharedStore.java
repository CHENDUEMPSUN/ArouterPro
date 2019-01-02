package com.example.lib_base.mvvm.utils;



import com.example.lib_base.MyApplication;

import java.io.Serializable;

public class SharedStore implements Serializable {

    public final static String SHARED_DATA_STORE_KEY = "SHARED_DATA_STORE_KEY";

    private boolean mInitFlag;

    public String redirectToUrl;

    public String redirectParamValue;

    public static SharedStore getInstance() {
        return MyApplication.getSharedDataStore();
    }

    public void init()
    {
        if (!mInitFlag)
            mInitFlag = true;
    }

    public boolean isInitialized()
    {
        return  mInitFlag;
    }
}
