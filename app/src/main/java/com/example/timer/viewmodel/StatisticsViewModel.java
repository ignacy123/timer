package com.example.timer.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.timer.businesslogic.timeprovider.TimeFormatter;
import com.example.timer.sql.StatisticsDao;

import javax.inject.Inject;

/**
 * Created by ignacy on 22.03.18.
 */

public class StatisticsViewModel extends ViewModel {

	private StatisticsDao statisticsDao;
	private TimeFormatter formatter;

	@Inject
	public StatisticsViewModel(StatisticsDao statisticsDao, TimeFormatter formatter) {
		this.statisticsDao = statisticsDao;
		this.formatter = formatter;
	}

	public LiveData<String> getBestSingle() {
		return Transformations.map(statisticsDao.getBestSingle(), bestSingle -> getString(bestSingle));
	}

	public LiveData<String> getBestMo3() {
		return Transformations.map(statisticsDao.getBestMo3(), bestMo3 -> getString(bestMo3));
	}

	public LiveData<String> getBestAvg5() {
		return Transformations.map(statisticsDao.getBestAvg5(), bestAvg5 -> getString(bestAvg5));
	}

	public LiveData<String> getBestAvg12() {
		return Transformations.map(statisticsDao.getBestAvg12(), bestAvg12 -> getString(bestAvg12));
	}

	public LiveData<String> getBestAvg50() {
		return Transformations.map(statisticsDao.getBestAvg50(), bestAvg50 -> getString(bestAvg50));
	}

	public LiveData<String> getBestAvg100() {
		return Transformations.map(statisticsDao.getBestAvg100(), bestAvg100 -> getString(bestAvg100));
	}

	private String getString(Long bestMo3) {
		if (bestMo3 == null) {
			return "-";
		}
		return formatter.formatTime(bestMo3);
	}
}
