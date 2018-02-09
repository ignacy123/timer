package com.example.timer.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.timer.model.Score;
import com.example.timer.sql.ScoreDAO;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ignacy on 09.02.18.
 */

public class ScoreViewModel extends ViewModel {

	private final LiveData<List<Score>> scores;

	@Inject
	public ScoreViewModel(ScoreDAO dao) {
		scores = dao.fetch();
	}

	public LiveData<List<Score>> getScores() {
		return scores;
	}
}
