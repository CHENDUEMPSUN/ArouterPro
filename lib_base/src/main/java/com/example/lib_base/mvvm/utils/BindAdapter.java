package com.example.lib_base.mvvm.utils;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;



public class BindAdapter {
    @BindingAdapter({"image_url"})
    public static void loadImage(ImageView view, String url) {
        if (!TextUtils.isEmpty(url)) {
            Glide.with(view.getContext()).load(url).into(view);
        }
    }

    @BindingAdapter({"image_crop_url"})
    public static void loadCropImage(ImageView view, String url) {
        if (!TextUtils.isEmpty(url)) {
            Bitmap bitmap = ImageUtils.getDiskBitmap(url);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageUtils.getIdRecognitionBitmap(bitmap).compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] bytes = baos.toByteArray();
            Glide.with(view.getContext()).load(bytes).into(view);
        }
    }
}
