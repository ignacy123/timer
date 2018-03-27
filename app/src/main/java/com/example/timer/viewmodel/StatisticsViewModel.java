package com.example.timer.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.timer.businesslogic.timeprovider.StatisticsGenerator;
import com.example.timer.model.Statistics;

import javax.inject.Inject;

/**
 * Created by ignacy on 22.03.18.
 */

public class StatisticsViewModel extends ViewModel {

	private StatisticsGenerator statisticsGenerator;

	MutableLiveData<Statistics> bestStatistics = new MutableLiveData<>();

	@Inject
	public StatisticsViewModel(StatisticsGenerator statisticsGenerator) {
		this.statisticsGenerator = statisticsGenerator;
	}

	public LiveData<Statistics> getBestStatistics() {
		bestStatistics.postValue(statisticsGenerator.getBestAverages());
		return bestStatistics;
	}
}
