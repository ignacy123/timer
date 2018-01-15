package com.example.timer.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.timer.businesslogic.timeprovider.ThreeByThreeScrambleGenerator;
import com.example.timer.model.Score;
import com.example.timer.sql.AppDatabase;
import com.example.timer.sql.ScoreDAO;
import com.example.timer.util.AppExecutors;
import com.example.timer.util.TimeCounter;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ignacy on 23.11.17.
 */

public class MainActivityViewModel extends AndroidViewModel {

	MutableLiveData<String> counter = new MutableLiveData<>();

	MutableLiveData<String> scramble = new MutableLiveData<>();
	private ThreeByThreeScrambleGenerator generator = new ThreeByThreeScrambleGenerator();

	// REVIEW:
	// elementy zwiazane z liczeniem powinny byc w jakiejs osobnej klasy
	public TimeCounter timeCounter = new TimeCounter();
	Timer t;

	public MainActivityViewModel(@NonNull Application application) {
		super(application);
		scoreDAO = AppDatabase.getInstance(application.getApplicationContext())
				.scoreDao();
		executors = AppExecutors.getInstance();
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
