package com.example.timer.di;

import com.example.timer.ui.main.CounterFragment;
import com.example.timer.ui.main.ScoreFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ignacy on 16.01.18.
 */

@Module
public abstract class ScoreFragmentModule {

	@ContributesAndroidInjector
	abstract ScoreFragment contributeScoreFragment();
}
