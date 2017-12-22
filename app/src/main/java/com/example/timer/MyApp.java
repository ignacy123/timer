package com.example.timer;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by ignacy on 22.12.17.
 */

public class MyApp extends Application {

	public void onCreate() {
		super.onCreate();
		Stetho.initializeWithDefaults(this);
	}
}
