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
import com.example.timer.businesslogic.timeprovider.GraphGenerator;
import com.example.timer.databinding.FragmentStatisticsBinding;
import com.example.timer.model.Score;
import com.example.timer.viewmodel.ScoreViewModel;
import com.example.timer.viewmodel.StatisticsViewModel;
import com.jjoe64.graphview.GraphView;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class StatisticsFragment extends Fragment {

	@Inject
	ViewModelProvider.Factory viewModelFactory;
	private StatisticsViewModel viewModel;
	private ScoreViewModel scoreViewModel;
	private FragmentStatisticsBinding binding;
	private View view;
	@Inject
	GraphGenerator graphGenerator;

	public StatisticsFragment() {

	}

	@Override
	public View onCreateView(final LayoutInflater inflater,
			@Nullable final ViewGroup container,
			@Nullable final Bundle savedInstanceState) {
		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_statistics, container, false);
		view = binding.getRoot();
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable final Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		viewModel = ViewModelProviders.of(this, viewModelFactory)
				.get(StatisticsViewModel.class);
		scoreViewModel = ViewModelProviders.of(this, viewModelFactory)
				.get(ScoreViewModel.class);
		scoreViewModel.getScores()
				.observe(this, values -> {
					createGraphFromStatistics(values);
				});
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

	private void createGraphFromStatistics(List<Score> values) {
		GraphView graph = view.findViewById(R.id.graph);
		graphGenerator.makeGraph(graph, values);
	}

	@Override
	public void onAttach(Context context) {
		AndroidSupportInjection.inject(this);
		super.onAttach(context);
	}

}
