package com.example.timer.util;

import android.arch.lifecycle.MutableLiveData;
import com.example.timer.businesslogic.timeprovider.TimeProvider;
import com.example.timer.viewmodel.MainViewModel;

import javax.inject.Inject;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ignacy on 03.01.18.
 */

public class TimeCounter {

	private MainViewModel viewModel;
	private final TimeProvider provider;
	long time1;
	long time2;
	Timer t;
	private String formattedTime;

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
		// TODO po pierwsze piszac kod formatujacy w tej klasie naruszasz zasade SRP: https://en.wikipedia.org/wiki/Single_responsibility_principle
		// a tak na prawde niepotrzebnie pisales samemu to formatowanie, mozna bylo do tego uzyc
		// np. SimpleDateFormat (dostepny w java 6) lub DateTimeFormatter (z java8, https://github.com/JakeWharton/ThreeTenABP)
		// warto to API znać i używać bo to podstawowe API Javy

		String formattedTime = "";
		formattedTime = formatSeconds((timeToFormat - timeToFormat % 1000) / 1000) + "." + String.valueOf(
				(timeToFormat - timeToFormat % 100) / 100 % 10) + String.valueOf((timeToFormat - timeToFormat % 10) / 10 % 10)
				+ String.valueOf(timeToFormat % 10);
		return formattedTime;
	}

	private String formatSeconds(long s) {
		if (s < 60) {
			return String.valueOf(s);
		}
		return String.valueOf((s - s % 60) / 60) + ":" + String.valueOf(s % 60);
	}

	public long provideDifference() {
		return (provider.provideTime() - time1);
	}

	public String getFormattedTime() {
		return formattedTime;
	}
}
