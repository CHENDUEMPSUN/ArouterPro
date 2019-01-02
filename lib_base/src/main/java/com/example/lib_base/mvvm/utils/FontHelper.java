package com.example.lib_base.mvvm.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextSwitcher;
import android.widget.TextView;

/**
 * 设置字体的工具类
 * @author  SUNL
 * @date 2016年8月7日
 */
public class FontHelper {
	/**
	 * 字体文件位置，如果为null则使用系统默认字体
	 */
	//	private static String FONT_NAME = "fonts/dong.otf";
	private static String FONT_NAME = null;
	
	private static Typeface typeface;
	
	public static void applyFont(final Context context, final View root) {
		if (FONT_NAME == null) {
			return;
		} try {
			if (root instanceof ViewGroup)
			{
				ViewGroup viewGroup = (ViewGroup)root;
				for (int i = 0; i < viewGroup.getChildCount(); i++)
				{
					applyFont(context, viewGroup.getChildAt(i));
				}
			}
			else if (root instanceof TextView)
			{
				if (typeface == null)
				{
					setTypeface(context);
				}
				((TextView)root).setTypeface(typeface);
			}
			else if (root instanceof TextSwitcher)
			{
				TextSwitcher textSwitcher = (TextSwitcher)root;
				for (int i = 0; i < textSwitcher.getChildCount(); i++)
				{
					TextView textView = (TextView)textSwitcher.getChildAt(i);
					textView.setTypeface(typeface);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void setTypeface(Context context)
	{
		if (FONT_NAME == null)
		{
			return;
		}
		if (typeface == null)
		{
			typeface = Typeface.createFromAsset(context.getAssets(), FONT_NAME);
		}
	}
}
