package com.example.timer.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.timer.R;
import com.example.timer.model.Score;

import java.util.ArrayList;
import java.util.List;

public class MyScoreRecyclerViewAdapter extends RecyclerView.Adapter<MyScoreRecyclerViewAdapter.ViewHolder> {

	private List<Score> scores = new ArrayList<>();

	public MyScoreRecyclerViewAdapter() {

	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
		notifyDataSetChanged();
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.fragment_score, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(final ViewHolder holder, int position) {
		Score score = scores.get(position);
		holder.mTimeView.setText(score.getFormattedTime());
		holder.mScrambleView.setText(score.getScramble());

	}

	@Override
	public int getItemCount() {
		return scores.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {

		public final View mView;
		public final TextView mTimeView;
		public final TextView mScrambleView;

		public ViewHolder(View view) {
			super(view);
			mView = view;
			mTimeView = (TextView) view.findViewById(R.id.time);
			mScrambleView = (TextView) view.findViewById(R.id.scramble);
		}

		@Override
		public String toString() {
			return super.toString() + " '" + mScrambleView.getText() + "'";
		}
	}
}
