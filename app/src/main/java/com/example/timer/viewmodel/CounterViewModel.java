package com.example.timer.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.timer.businesslogic.timeprovider.ThreeByThreeScrambleGeneratorImpl;
import com.example.timer.model.Score;
import com.example.timer.sql.ScoreDAO;
import com.example.timer.util.AppExecutors;
import com.example.timer.util.TimeCounter;

import javax.inject.Inject;

/**
 * Created by ignacy on 23.11.17.
 */

public class CounterViewModel extends ViewModel {

	MutableLiveData<String> counter = new MutableLiveData<>();

	MutableLiveData<String> scramble = new MutableLiveData<>();
	private ThreeByThreeScrambleGeneratorImpl generator = new ThreeByThreeScrambleGeneratorImpl();

	private final TimeCounter timeCounter;

	@Inject
	public CounterViewModel(TimeCounter timeCounter, ScoreDAO scoreDAO, AppExecutors executors) {
		this.timeCounter = timeCounter;
		this.scoreDAO = scoreDAO;
		this.executors = executors;
		scramble.postValue(generator.generate());
	}

	public LiveData<String> getCounter() {
		return counter;
	}

	public LiveData<String> getScramble() {
		return scramble;
	}

	ScoreDAO scoreDAO;
	AppExecutors executors;

	public void startCounting() {
		timeCounter.startCounting(counter);

	}

	//	public void changeTimerValue();

	public void stopCounting() {
		long l = timeCounter.stopCounting();
		Score score = new Score(scramble.getValue(), l, timeCounter.getFormattedTime());
		counter.postValue(String.valueOf(timeCounter.getFormattedTime()));
		executors.diskIO()
				.execute(() -> scoreDAO.persist(score));
		scramble.postValue(generator.generate());

	}

}
