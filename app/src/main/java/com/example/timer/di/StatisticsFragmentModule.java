package com.example.timer.di;

import com.example.timer.ui.main.StatisticsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ignacy on 22.03.18.
 */

@Module
public abstract class StatisticsFragmentModule {

	@ContributesAndroidInjector
	abstract StatisticsFragment contributeStatisticsFragment();
}
