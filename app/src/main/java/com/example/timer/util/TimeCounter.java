package com.example.timer.util;

import android.arch.lifecycle.MutableLiveData;

import com.example.timer.businesslogic.timeprovider.TimeProvider;
import com.example.timer.viewmodel.MainViewModel;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

/**
 * Created by ignacy on 03.01.18.
 */

public class TimeCounter {

	private MainViewModel viewModel;
	private final TimeProvider provider;
	long time1;
	long time2;
	long difference;
	Timer t;

	@Inject
	public TimeCounter(TimeProvider provider) {
		this.provider = provider;
	}

	public void startCounting(MutableLiveData<String> counter) {
		time1 = provider.provideTime();
		t = new Timer();
		t.schedule(new TimerTask() {

			@Override
			public void run() {
				counter.postValue(String.valueOf(provideDifference()));
			}
		}, 0, 10);

	}

	public long stopCounting() {

		t.cancel();
		time2 = provider.provideTime();
		return (time2 - time1);
	}

	public long provideDifference() {
		return (provider.provideTime() - time1);
	}

}
