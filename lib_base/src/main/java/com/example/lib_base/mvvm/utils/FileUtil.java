package com.example.lib_base.mvvm.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class FileUtil {
    public static final String PATH = "/mypicture/con/";
    public static File getSaveFile(Context context) {
        return new File(context.getFilesDir(), "pic.jpg");
    }

    public static File getHeadFile() {
        return new File(Environment.getExternalStorageDirectory()+PATH, "head.jpg");
    }


}
