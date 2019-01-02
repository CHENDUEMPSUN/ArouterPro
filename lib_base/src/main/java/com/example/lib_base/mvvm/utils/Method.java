package com.example.lib_base.mvvm.utils;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.PowerManager;

public class Method
{
	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public static boolean isScreenOn(Context context)
	{
		PowerManager pm = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
		boolean isScreenOn = true;//如果为true，则表示屏幕“亮”了，否则屏幕“暗”了。
		if (Build.VERSION.SDK_INT < 20)
		{
			isScreenOn = pm.isScreenOn();
		}
		else
		{
			isScreenOn = pm.isInteractive();
		}
		return isScreenOn;
	}
	
	/**
	 * 是否有sd卡
	 * 
	 * @return

	public static boolean hasSdcard()
	{
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	} */
	
	/**
	 * 判断是否后台运行
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isRunningPackName(Context context)
	{
		String packageName = context.getPackageName();
		if (Method.getRunningPackName(context).equals(packageName))
		{
			return true;
		}
		return false;
	}
	
	public static String getRunningPackName(Context context)
	{
		ActivityManager manager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
		/** 获取当前正在运行的任务栈列表， 越是靠近当前运行的任务栈会被排在第一位，之后的以此类推 */
		List<RunningTaskInfo> runningTasks = manager.getRunningTasks(1);
		
		/** 获得当前最顶端的任务栈，即前台任务栈 */
		RunningTaskInfo runningTaskInfo = runningTasks.get(0);
		
		/** 获取前台任务栈的最顶端 Activity */
		ComponentName topActivity = runningTaskInfo.baseActivity;
		
		/** 获取应用的包名 */
		String packageName = topActivity.getPackageName();
		return packageName;
	}
	
	/**
	 * 关闭软键盘

	public static void closeKey(Activity activity)
	{
		InputMethodManager inputMethodManager = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (activity.getCurrentFocus() != null)
		{
			inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
				InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}*/
	
	/**
	 * 打开软键盘

	public static void openKey(Context context, final EditText inputView)
	{
		final InputMethodManager inputMethodManager = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
		new Timer().schedule(new TimerTask()
		{
			
			@Override
			public void run()
			{
				inputView.setFocusable(true);
				inputView.setFocusableInTouchMode(true);
				inputView.requestFocus();
				inputMethodManager.showSoftInput(inputView, InputMethodManager.SHOW_IMPLICIT);
			}
		}, 300);
	} */
	
	/**
	 * 获取手机版本

	public static int getAndroidSDKVersion()
	{
		int version = 0;
		try
		{
			version = Integer.valueOf(android.os.Build.VERSION.SDK);
		}
		catch (NumberFormatException e)
		{
		
		}
		return version;
	}
	*/
	/**
	 * 获取手机版本

	public static String getAndroidVersion()
	{
		String version = null;
		try
		{
			version = android.os.Build.VERSION.RELEASE;
		}
		catch (NumberFormatException e)
		{
		
		}
		return version;
	}
	*/
	/**
	 * 获取手机厂商

	public static String getBrand()
	{
		String brand = null;
		try
		{
			brand = android.os.Build.BRAND;
		}
		catch (NumberFormatException e)
		{
		
		}
		return brand;
	}
	*/
	/**
	 * 获取手机型号

	public static String getModel()
	{
		String brand = null;
		try
		{
			brand = android.os.Build.MODEL;
		}
		catch (NumberFormatException e)
		{
		
		}
		return brand;
	}*/
	
//	/**如果返回码为000002，表示token失效，需要先登录*/
//	public static void login(Context context)
//	{
//		Intent intent = new Intent(context, LoginActivity.class);
//		context.startActivity(intent);
//	}
	
	/**检测手机是否安装某个应用
	public static boolean checkPackage(Context context, String packageName)
	{
		//获取手机中已安装的应用程序
		List<ApplicationInfo> mAppList = context.getPackageManager().getInstalledApplications(0);
		for (ApplicationInfo appInfo : mAppList)
		{
			if (appInfo.packageName.equals(FormatUtil.isEmpty(packageName) ? "" : packageName))
			{
				return true;
			}
		}
		return false;
	}*/

	
	/***
	 * 获取当前正在运行的进程
	 * @param context
	 * @return

	public static List<String> getRunningProcess(Context context)
	{
		List<String> mAppNameList = new ArrayList<String>(DMConstant.IntConstant.INIT_VICTOR);
		ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
		//获取正在运行的应用  
		List<RunningAppProcessInfo> run = am.getRunningAppProcesses();
		for (RunningAppProcessInfo ra : run)
		{
			//这里主要是过滤系统的应用和电话应用，当然你也可以把它注释掉。  
			if (ra.processName.equals("system") || ra.processName.equals("com.android.phone"))
			{
				continue;
			}
			mAppNameList.add(ra.processName);
		}
		return mAppNameList;
	} */
}
