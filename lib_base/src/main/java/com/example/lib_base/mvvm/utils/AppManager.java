package com.example.lib_base.mvvm.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;


import java.util.Stack;

public class AppManager<T extends Activity>
{
	
	private static Stack<Activity> activityStack = new Stack<>();
	
	private static AppManager instance;
	
	private AppManager(){}
	/**
	 * 单一实例
	 */
	public static AppManager getAppManager()
	{
		if (instance == null)
		{
			instance = new AppManager();
		}
		return instance;
	}
	
	/** 添加Activity到堆栈 */
	public void addActivity(T activity)
	{
		activityStack.add(activity);
	}
	public void removeActivity(T activity) {
		activityStack.remove(activity);
	}

	/**
	 * 获取当前Activity（堆栈中最后一个压入的）
	 * 该方法最好不要再非Activity或Fragment中调用
	 */
	public Activity currentActivity()
	{
		if (activityStack == null || activityStack.isEmpty())
			return null;

		Activity activity = activityStack.lastElement();
		return activity;
	}
	
	/**
	 * 结束当前Activity（堆栈中最后一个压入的）
	 */
	public void finishActivity()
	{
		Activity activity = activityStack.lastElement();
		finishActivity(activity);
	}
	
	/**
	 * 结束指定的Activity
	 */
	public void finishActivity(Activity activity)
	{
		if (null != activity && !activity.isFinishing())
		{
			activityStack.remove(activity);
			activity.finish();
		}
	}
	
	/**
	 * 结束指定类名的Activity
	 */
	public void finishActivity(Class<?> cls)
	{
		for (Activity activity : activityStack)
		{
			if (activity.getClass().equals(cls))
			{
				finishActivity(activity);
								break;
			}
		}
	}

	public void finishOtherActivity(Class<T>... cls)
	{
		for (Class clz: cls) {
			Log.i("finishOtherActivity","finishOtherActivity:" + clz.getSimpleName());

			finishActivity(getActivity(clz));
		}
//		for (Activity activity : activityStack)
//		{
//			if (activity.getClass().equals(cls))
//			{
//				finishActivity(activity);
//				break;
//			}
//		}
	}
	
	/**
	 * 返回activity数量
	 */
	public int getCount()
	{
		if (activityStack != null)
		{
			return activityStack.size();
		}
		else
		{
			return 0;
		}
		
	}
	
	/**
	 * 结束所有Activity
	 */
	public void finishAllActivity()
	{
		for (Activity activity : activityStack){
			if (null != activity && !activity.isFinishing()){
				activity.finish();
			}
		}

		activityStack.clear();
	}

	/**
	 * 获取指定的Activity
	 * 
	 * @author kymjs

	public Activity getActivity(Class<?> cls)
	{
		if (activityStack != null)
			for (Activity activity : activityStack)
			{
				if (activity.getClass().equals(cls))
				{
					return activity;
				}
			}
		return null;
	}*/

	public T getActivity(Class<T> cls)
	{
		if (activityStack != null)
			for (Activity activity : activityStack)
			{
				if (activity.getClass().equals(cls))
				{
					return (T)activity;
				}
			}
		return null;
	}

	public void printAllActivity(){
		for (Activity activity : activityStack){
			Log.i("printAllActivity",activity.getClass().getSimpleName());
		}
	}

	

	/**
	 * 退出应用程序
	 */
	public void AppExit(Context context)
	{
		try
		{
			finishAllActivity();
			// 友盟的方法，在调用杀死线程之类的方法之前调用
//			MobclickAgent.onKillProcess(context);
//			Session.onKillProcess();

			// 杀死该应用进程
			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(0);
		}
		catch (Exception e)
		{
		}
	}
}