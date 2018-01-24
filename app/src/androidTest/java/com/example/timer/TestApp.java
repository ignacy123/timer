package com.example.timer;

import android.app.Application;
import android.support.v4.app.Fragment;
import dagger.android.AndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

import static org.mockito.Mockito.mock;

/**
 * Created by ignacy on 16.01.18.
 */

public class TestApp extends Application implements HasSupportFragmentInjector {

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public AndroidInjector<Fragment> supportFragmentInjector() {
		return mock(AndroidInjector.class);
	}
}
