package com.example.timer.ui.main;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.timer.R;
import com.example.timer.model.Score;
import com.example.timer.viewmodel.ScoreViewModel;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class ScoreFragment extends Fragment implements MyScoreRecyclerViewAdapter.ScoreLongClickListener {

	@Inject
	ViewModelProvider.Factory viewModelFactory;
	ScoreViewModel viewModel;

	private static final String ARG_COLUMN_COUNT = "column-count";
	RecyclerView recyclerView;
	private MyScoreRecyclerViewAdapter adapter = new MyScoreRecyclerViewAdapter(this);

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public ScoreFragment() {
	}

	public static ScoreFragment newInstance(int columnCount) {
		ScoreFragment fragment = new ScoreFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_COLUMN_COUNT, columnCount);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_score_list, container, false);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		recyclerView.setAdapter(adapter);
		return recyclerView;
	}

	@Override
	public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		viewModel = ViewModelProviders.of(this, viewModelFactory)
				.get(ScoreViewModel.class);
		viewModel.getScores()
				.observe(this, scores -> rotateAndSetScores(scores));
	}

	private void rotateAndSetScores(List<Score> scores) {
		Collections.reverse(scores);
		adapter.setScores(scores);
	}

	@Override
	public void onAttach(Context context) {
		AndroidSupportInjection.inject(this);
		super.onAttach(context);
	}

	@Override
	public void onLongClick(Score score) {

		new AlertDialog.Builder(getContext())//
				.setMessage("Do you want to remove this time?")//TODO
				.setPositiveButton("Yes", (dialogInterface, i) -> viewModel.removeScore(score))
				.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.dismiss())
				.show();
	}
}