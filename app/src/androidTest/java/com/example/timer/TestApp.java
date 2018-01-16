package com.example.timer;

import android.app.Activity;
import android.app.Application;

import dagger.android.AndroidInjector;
import dagger.android.HasActivityInjector;

import static org.mockito.Mockito.mock;

/**
 * Created by ignacy on 16.01.18.
 */

public class TestApp extends Application implements HasActivityInjector {

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public AndroidInjector<Activity> activityInjector() {
		return mock(AndroidInjector.class);
	}
}
