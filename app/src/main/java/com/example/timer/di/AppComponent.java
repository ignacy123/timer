package com.example.timer.di;

/**
 * Created by ignacy on 16.01.18.
 */

import android.app.Application;

import com.example.timer.MyApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = { AndroidInjectionModule.class, AppModule.class, MainActivityModule.class, ScoreFragmentModule.class,
		CounterFragmentModule.class, StatisticsFragmentModule.class })
public interface AppComponent {

	@Component.Builder
	interface Builder {

		@BindsInstance
		Builder application(Application application);

		AppComponent build();
	}

	void inject(MyApp application);
}
