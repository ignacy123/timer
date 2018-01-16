package com.example.timer.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.timer.businesslogic.timeprovider.ThreeByThreeScrambleGeneratorImpl;
import com.example.timer.model.Score;
import com.example.timer.sql.ScoreDAO;
import com.example.timer.util.AppExecutors;
import com.example.timer.util.TimeCounter;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

/**
 * Created by ignacy on 23.11.17.
 */

public class MainActivityViewModel extends ViewModel {

	MutableLiveData<String> counter = new MutableLiveData<>();

	MutableLiveData<String> scramble = new MutableLiveData<>();
	private ThreeByThreeScrambleGeneratorImpl generator = new ThreeByThreeScrambleGeneratorImpl();

	// REVIEW:
	// elementy zwiazane z liczeniem powinny byc w jakiejs osobnej klasy
	private final TimeCounter timeCounter;
	Timer t;

	@Inject
	public MainActivityViewModel(TimeCounter timeCounter, ScoreDAO scoreDAO, AppExecutors executors) {
		this.timeCounter = timeCounter;
		this.scoreDAO = scoreDAO;
		this.executors = executors;
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
		timeCounter.startCounting();
		t = new Timer();
		t.schedule(new TimerTask() {

			@Override
			public void run() {
				counter.postValue(String.valueOf(timeCounter.provideDifference()));
			}
		}, 0, 10);

	}

	public void stopCounting() {
		counter.postValue(String.valueOf(timeCounter.stopCounting()));
		scramble.postValue(generator.generate());
		t.cancel();
		Score score = new Score("", timeCounter.stopCounting());
		executors.diskIO()
				.execute(() -> scoreDAO.persist(score));

	}

	public void setScramble() {
	}

}
