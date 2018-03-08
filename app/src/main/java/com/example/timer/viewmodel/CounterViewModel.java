package com.example.timer.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.timer.businesslogic.timeprovider.StatisticsGenerator;
import com.example.timer.businesslogic.timeprovider.ThreeByThreeScrambleGeneratorImpl;
import com.example.timer.model.Score;
import com.example.timer.sql.ScoreDAO;
import com.example.timer.util.AppExecutors;
import com.example.timer.util.TimeCounter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ignacy on 23.11.17.
 */

public class CounterViewModel extends ViewModel {

	MutableLiveData<String> counter = new MutableLiveData<>();

	MutableLiveData<String> scramble = new MutableLiveData<>();

	MutableLiveData<String> mo3 = new MutableLiveData<>();

	MutableLiveData<String> avg5 = new MutableLiveData<>();

	MutableLiveData<String> avg12 = new MutableLiveData<>();

	MutableLiveData<String> avg50 = new MutableLiveData<>();

	MutableLiveData<String> avg100 = new MutableLiveData<>();
	private ThreeByThreeScrambleGeneratorImpl generator = new ThreeByThreeScrambleGeneratorImpl();

	private StatisticsGenerator statisticsGenerator;

	private final TimeCounter timeCounter;

	private final LiveData<List<Score>> scores;

	@Inject
	public CounterViewModel(TimeCounter timeCounter, ScoreDAO scoreDAO, AppExecutors executors, StatisticsGenerator statisticsGenerator) {
		this.timeCounter = timeCounter;
		this.scoreDAO = scoreDAO;
		this.executors = executors;
		this.statisticsGenerator = statisticsGenerator;
		scramble.postValue(generator.generate());
		scores = scoreDAO.fetch();
	}

	public LiveData<String> getCounter() {
		return counter;
	}

	public LiveData<String> getScramble() {
		return scramble;
	}

	public MutableLiveData<String> getMo3() {
		return mo3;
	}

	public MutableLiveData<String> getAvg5() {
		return avg5;
	}

	public MutableLiveData<String> getAvg12() {
		return avg12;
	}

	public MutableLiveData<String> getAvg50() {
		return avg50;
	}

	public MutableLiveData<String> getAvg100() {
		return avg100;
	}

	public LiveData<List<Score>> getScores() {
		return scores;
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

	public void updateAverages(List<Score> scores) {
		mo3.postValue(timeCounter.returnFormattedTime((long) statisticsGenerator.generateAverages(scores)
				.getMo3()));
		avg5.postValue(timeCounter.returnFormattedTime((long) statisticsGenerator.generateAverages(scores)
				.getAvg5()));
		avg12.postValue(timeCounter.returnFormattedTime((long) statisticsGenerator.generateAverages(scores)
				.getAvg12()));
		avg50.postValue(timeCounter.returnFormattedTime((long) statisticsGenerator.generateAverages(scores)
				.getAvg50()));
		avg100.postValue(timeCounter.returnFormattedTime((long) statisticsGenerator.generateAverages(scores)
				.getAvg100()));
	}
}
