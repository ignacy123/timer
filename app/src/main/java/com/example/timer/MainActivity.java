package com.example.timer;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.timer.databinding.ActivityMainBinding;
import com.example.timer.viewmodel.MainActivityViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

	@Inject
	ViewModelProvider.Factory viewModelFactory;
	private MainActivityViewModel viewModel;
	ActivityMainBinding binding;
	private boolean counting;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		AndroidInjection.inject(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
		viewModel = ViewModelProviders.of(this, viewModelFactory)
				.get(MainActivityViewModel.class);
		viewModel.getCounter()
				.observe(this, counterValue -> {
					binding.setCounter(counterValue);
				});
		viewModel.getScramble()
				.observe(this, scrambleValue -> {
					binding.setScramble(scrambleValue);
				});
	}

	public void startCounting(View view) {
		if (counting) {
			viewModel.setScramble();
			viewModel.stopCounting();
			counting = false;
		} else {
			viewModel.startCounting();
			counting = true;
		}

	}
}