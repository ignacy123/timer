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
import com.example.timer.viewmodel.StatisticsViewModel;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class StatisticsFragment extends Fragment {

	@Inject
	ViewModelProvider.Factory viewModelFactory;
	private StatisticsViewModel viewModel;
	private FragmentCounterBinding binding;

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
		GraphView graph = (GraphView) findViewById(R.id.graph);
		LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
				new DataPoint(0, 1),
				new DataPoint(1, 5),
				new DataPoint(2, 3),
				new DataPoint(3, 2),
				new DataPoint(4, 6)
		});
		graph.addSeries(series);
		super.onActivityCreated(savedInstanceState);
		viewModel = ViewModelProviders.of(this, viewModelFactory)
				.get(StatisticsViewModel.class);
		viewModel.getBestStatistics()
				.observe(this, statistics -> {
					binding.setStatistics(statistics);
					binding.executePendingBindings();
				});
	}

	@Override
	public void onAttach(Context context) {
		AndroidSupportInjection.inject(this);
		super.onAttach(context);
	}

}
