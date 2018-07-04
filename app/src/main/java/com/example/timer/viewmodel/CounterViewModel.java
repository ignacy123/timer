package com.example.timer.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.timer.businesslogic.timeprovider.FourByFourScrambleGeneratorImpl;
import com.example.timer.businesslogic.timeprovider.ScrambleGenerator;
import com.example.timer.businesslogic.timeprovider.StatisticsGenerator;
import com.example.timer.model.Score;
import com.example.timer.model.Statistics;
import com.example.timer.sql.ScoreDao;
import com.example.timer.sql.StatisticsDao;
import com.example.timer.util.AppExecutors;
import com.example.timer.util.TimeCounter;

import javax.inject.Inject;

/**
 * Created by ignacy on 23.11.17.
 */

public class CounterViewModel extends ViewModel {

	MutableLiveData<String> counter = new MutableLiveData<>();

	MutableLiveData<String> scramble = new MutableLiveData<>();

	public void setCurrentScrambleGenerator(ScrambleGenerator currentScrambleGenerator) {
		this.currentScrambleGenerator = currentScrambleGenerator;
		scramble.postValue(currentScrambleGenerator.generate());
	}

	private ScrambleGenerator currentScrambleGenerator = new FourByFourScrambleGeneratorImpl();

	private StatisticsGenerator statisticsGenerator;
	private StatisticsDao statisticsDao;

	private final TimeCounter timeCounter;

	@Inject
	public CounterViewModel(TimeCounter timeCounter,
			ScoreDao scoreDao,
			AppExecutors executors,
			StatisticsGenerator statisticsGenerator,
			StatisticsDao statisticsDao) {
		this.timeCounter = timeCounter;
		this.scoreDao = scoreDao;
		this.executors = executors;
		this.statisticsGenerator = statisticsGenerator;
		this.statisticsDao = statisticsDao;
		scramble.postValue(currentScrambleGenerator.generate());
	}

	public LiveData<String> getCounter() {
		return counter;
	}

	public LiveData<String> getScramble() {
		return scramble;
	}

	public LiveData<Statistics> getStatistics() {
		return Transformations.map(scoreDao.fetch(), scores -> {

			Statistics statistics = statisticsGenerator.generateAverages(scores);
			executors.diskIO()
					.execute(() -> statisticsDao.persist(statistics));
			return statistics;

		});
	}

	ScoreDao scoreDao;
	AppExecutors executors;

	public void startCounting() {
		timeCounter.startCounting(counter);

	}

	public void stopCounting() {
		long l = timeCounter.stopCounting();
		Score score = new Score(0, scramble.getValue(), l, timeCounter.getFormattedTime());
		counter.postValue(String.valueOf(timeCounter.getFormattedTime()));
		executors.diskIO()
				.execute(() -> scoreDao.persist(score));
		scramble.postValue(currentScrambleGenerator.generate());

	}
}
