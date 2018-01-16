package com.example.timer.di;

import com.example.timer.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ignacy on 16.01.18.
 */

@Module
public abstract class MainActivityModule {

	@ContributesAndroidInjector
	abstract MainActivity contributeMainActivity();
}
