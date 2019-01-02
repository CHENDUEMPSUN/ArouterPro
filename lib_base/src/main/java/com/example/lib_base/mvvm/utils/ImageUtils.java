package com.example.lib_base.mvvm.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;

public class ImageUtils {

    public static Bitmap getDiskBitmap(String path) {
        Bitmap bitmap = null;
        try {
            File file = new File(path);
            if (file.exists()) {
                bitmap = BitmapFactory.decodeFile(path);
            }
        } catch (Exception e) {

        }
        return bitmap;
    }

    /**
     * 身份识别裁剪
     */
    public static Bitmap getIdRecognitionBitmap(Bitmap bitmap) {
        int w = bitmap.getWidth(); // 得到图片的宽，高
        int h = bitmap.getHeight();
        return Bitmap.createBitmap(bitmap, (int) (601f / 1006 * w), (int) (110f / 632 * h), (int) (362f / 1006 * w), (int) (366f / 632 * h), null, false);
    }


}
