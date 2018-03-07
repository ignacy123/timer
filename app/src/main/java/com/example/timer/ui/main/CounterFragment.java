package com.example.timer.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.timer.R;
import com.example.timer.databinding.FragmentCounterBinding;
import com.example.timer.viewmodel.CounterViewModel;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class CounterFragment extends Fragment {

	@Inject
	ViewModelProvider.Factory viewModelFactory;
	private CounterViewModel viewModel;
	private FragmentCounterBinding binding;
	private boolean counting;

	@Override
	public void onAttach(final Context context) {
		AndroidSupportInjection.inject(this);
		super.onAttach(context);
	}

	@Override
	public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		viewModel = ViewModelProviders.of(this, viewModelFactory)
				.get(CounterViewModel.class);
		viewModel.getCounter()
				.observe(this, counterValue -> {
					binding.setCounter(counterValue);
					binding.executePendingBindings();
				});
		viewModel.getScramble()
				.observe(this, scrambleValue -> {
					binding.setScramble(scrambleValue);
					binding.executePendingBindings();
				});
	}

	@Nullable
	@Override
	public View onCreateView(final LayoutInflater inflater,
			@Nullable final ViewGroup container,
			@Nullable final Bundle savedInstanceState) {
		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_counter, container, false);
		View view = binding.getRoot();
		initViews(view);
		return view;
	}

	private void initViews(final View view) {
		view.findViewById(R.id.areaToClick)
				.setOnClickListener(v -> startCounting());
	}

	private void startCounting() {
		if (counting) {
			viewModel.stopCounting();
			counting = false;
		} else {
			viewModel.startCounting();
			counting = true;
		}

	}
}
