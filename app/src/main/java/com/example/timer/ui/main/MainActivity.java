package com.example.timer.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.timer.R;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {

	@Inject
	DispatchingAndroidInjector<Fragment> fragmentInjector;

	@Override
	protected void onCreate(@Nullable final Bundle savedInstanceState) {
		AndroidInjection.inject(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (null == savedInstanceState) {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.frame, new CounterFragment())
					.commitNow();
		}

	}

	@Override
	public AndroidInjector<Fragment> supportFragmentInjector() {
		return fragmentInjector;
	}

}