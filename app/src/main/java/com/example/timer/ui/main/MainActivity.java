package com.example.timer.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.timer.R;
import com.example.timer.ui.settings.SettingsActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {

	@Inject
	ViewModelProvider.Factory viewModelFactory;
	@Inject
	DispatchingAndroidInjector<Fragment> fragmentInjector;
	@BindView(R.id.pager)
	ViewPager pager;
	@BindView(R.id.action_settings)
	ImageView imageView;
	@BindView(R.id.categorySpinner)
	Spinner spinner;

	String currentPuzzle = "3x3";

	@Override
	protected void onCreate(@Nullable final Bundle savedInstanceState) {
		AndroidInjection.inject(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		if (null == savedInstanceState) {
			pager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
			pager.setCurrentItem(1);
		}
		Intent intent = new Intent(this, SettingsActivity.class);

		imageView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				startActivity(intent);
			}
		});

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.cubes, R.layout.spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
				if (position == 0) {
					currentPuzzle = "3x3"; //TODO
				}
				if (position == 1) {
					currentPuzzle = "4x4"; //TODO
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {

			}
		});
	}

	@Override
	public AndroidInjector<Fragment> supportFragmentInjector() {
		return fragmentInjector;
	}

}