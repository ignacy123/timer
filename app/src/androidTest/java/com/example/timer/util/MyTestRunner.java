package com.example.timer.util;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

import com.example.timer.TestApp;

/**
 * Created by ignacy on 16.01.18.
 */

public class MyTestRunner extends AndroidJUnitRunner {

	@Override
	public Application newApplication(ClassLoader cl, String className, Context context)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return super.newApplication(cl, TestApp.class.getName(), context);
	}
}
