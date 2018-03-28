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
import com.example.timer.databinding.FragmentStatisticsBinding;
import com.example.timer.viewmodel.StatisticsViewModel;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class StatisticsFragment extends Fragment {

	@Inject
	ViewModelProvider.Factory viewModelFactory;
	private StatisticsViewModel viewModel;
	private FragmentStatisticsBinding binding;

	public StatisticsFragment() {

	}

	@Override
	public View onCreateView(final LayoutInflater inflater,
			@Nullable final ViewGroup container,
			@Nullable final Bundle savedInstanceState) {
		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_statistics, container, false);
		View view = binding.getRoot();
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
		//		GraphView graph = (GraphView) findViewById(R.id.graph);
		//		LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
		//				new DataPoint(0, 1),
		//				new DataPoint(1, 5),
		//				new DataPoint(2, 3),
		//				new DataPoint(3, 2),
		//				new DataPoint(4, 6)
		//		});
		//		graph.addSeries(series);
		super.onActivityCreated(savedInstanceState);
		viewModel = ViewModelProviders.of(this, viewModelFactory)
				.get(StatisticsViewModel.class);
		viewModel.getBestSingle()
				.observe(this, single -> {
					binding.setSingle(single);
					binding.executePendingBindings();
				});
		viewModel.getBestMo3()
				.observe(this, value -> {
					binding.setMo3(value);
					binding.executePendingBindings();
				});
		viewModel.getBestAvg5()
				.observe(this, value -> {
					binding.setAvg5(value);
					binding.executePendingBindings();
				});
		viewModel.getBestAvg12()
				.observe(this, value -> {
					binding.setAvg12(value);
					binding.executePendingBindings();
				});
		viewModel.getBestAvg50()
				.observe(this, value -> {
					binding.setAvg50(value);
					binding.executePendingBindings();
				});
		viewModel.getBestAvg100()
				.observe(this, value -> {
					binding.setAvg100(value);
					binding.executePendingBindings();
				});
	}

	@Override
	public void onAttach(Context context) {
		AndroidSupportInjection.inject(this);
		super.onAttach(context);
	}

}
