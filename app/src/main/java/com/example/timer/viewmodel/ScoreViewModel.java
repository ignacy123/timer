package com.example.timer.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.timer.model.Score;
import com.example.timer.sql.ScoreDao;
import com.example.timer.util.AppExecutors;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ignacy on 09.02.18.
 */

public class ScoreViewModel extends ViewModel {

	private final ScoreDao dao;
	private final AppExecutors executors;

	@Inject
	public ScoreViewModel(ScoreDao dao, AppExecutors executors) {
		this.dao = dao;
		this.executors = executors;
	}

	public void removeScore(Score score) {
		executors.diskIO()
				.execute(() -> dao.delete(score));
	}

	public LiveData<List<Score>> getScores() {
		return dao.fetch();
	}
}
