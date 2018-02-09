package com.example.timer.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.timer.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {

	@Inject
	DispatchingAndroidInjector<Fragment> fragmentInjector;
	@BindView(R.id.pager)
	ViewPager pager;

	@Override
	protected void onCreate(@Nullable final Bundle savedInstanceState) {
		AndroidInjection.inject(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		if (null == savedInstanceState) {
			pager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
		}

	}

	@Override
	public AndroidInjector<Fragment> supportFragmentInjector() {
		return fragmentInjector;
	}

}