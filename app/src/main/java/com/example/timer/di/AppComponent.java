package com.example.timer.di;

/**
 * Created by ignacy on 16.01.18.
 */

import android.app.Application;
import com.example.timer.MyApp;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

import javax.inject.Singleton;

@Singleton
@Component(modules = { AndroidInjectionModule.class, AppModule.class, MainActivityModule.class, MainFragmentModule.class })
public interface AppComponent {

	@Component.Builder
	interface Builder {

		@BindsInstance
		Builder application(Application application);

		AppComponent build();
	}

	void inject(MyApp application);
}
