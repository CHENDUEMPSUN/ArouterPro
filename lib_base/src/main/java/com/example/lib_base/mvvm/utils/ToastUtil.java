package com.example.lib_base.mvvm.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @author  SUNL
 * @date 2016年6月21日
 */
public class ToastUtil
{
	private static ToastUtil util;
	
	public static ToastUtil getInstant()
	{
		if (util == null)
		{
			util = new ToastUtil();
		}
		return util;
	}
	
	private Toast toast;
	
	/**
	 * 显示Toast
	 * @param context
	 * @param text
	 */
	public void show(Context context, CharSequence text) {

		if (toast == null)
		{
			toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		}
		else
		{
			toast.setText(text);
			toast.setDuration(Toast.LENGTH_SHORT);
		}
		toast.show();
	}
	/**
	 * 显示Toast(long)
	 * @param context
	 * @param text
	 */
	public void showLong(Context context, CharSequence text) {

		if (toast == null)
		{
			toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
		}
		else
		{
			toast.setText(text);
			toast.setDuration(Toast.LENGTH_LONG);
		}
		toast.show();
	}
	
	/**
	 * 显示Toast
	 * @param context
	 */
	public void show(Context context, int resId) {
		show(context,context.getText(resId));
	}
}
