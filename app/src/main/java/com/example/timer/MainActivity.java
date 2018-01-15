package com.example.timer;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.timer.databinding.ActivityMainBinding;
import com.example.timer.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

	public MainActivityViewModel viewModel; //TODO
	ActivityMainBinding binding;
	private boolean counting;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
		viewModel = ViewModelProviders.of(this)
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