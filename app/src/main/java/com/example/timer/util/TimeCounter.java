package com.example.timer.util;

import android.arch.lifecycle.MutableLiveData;

import com.example.timer.businesslogic.timeprovider.TimeFormatter;
import com.example.timer.businesslogic.timeprovider.TimeProvider;
import com.example.timer.viewmodel.CounterViewModel;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

/**
 * Created by ignacy on 03.01.18.
 */

public class TimeCounter {

	private CounterViewModel viewModel;
	private final TimeProvider provider;
	private final TimeFormatter formater;
	long time1;
	long time2;
	Timer t;
	private String formattedTime;

	@Inject
	public TimeCounter(TimeProvider provider, TimeFormatter formater) {
		this.provider = provider;
		this.formater = formater;
	}

	public void startCounting(MutableLiveData<String> counter) {
		time1 = provider.provideTime();
		t = new Timer();
		t.schedule(new TimerTask() {

			@Override
			public void run() {
				counter.postValue(returnFormattedTime(provideDifference()));
			}
		}, 0, 10);

	}

	public long stopCounting() {

		t.cancel();
		time2 = provider.provideTime();
		formattedTime = returnFormattedTime(time2 - time1);
		return (time2 - time1);

	}

	public String returnFormattedTime(long timeToFormat) {
		return formater.chooseTimeFormat(timeToFormat);
	}

	public long provideDifference() {
		return (provider.provideTime() - time1);
	}

	public String getFormattedTime() {
		return formattedTime;
	}
}
