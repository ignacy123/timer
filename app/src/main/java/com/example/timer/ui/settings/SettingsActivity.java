package com.example.timer.ui.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ToggleButton;

import com.example.timer.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class SettingsActivity extends AppCompatActivity {

	@BindView(R.id.toggleButton)
	ToggleButton toggleButton;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		ButterKnife.bind(this);
		toggleButton.setOnCheckedChangeListener((compoundButton, isToggled) -> {
			//TODO inspection
			Timber.d("%s", isToggled);
		});
	}
}
