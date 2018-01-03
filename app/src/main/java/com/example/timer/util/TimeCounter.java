package com.example.timer.util;

import com.example.timer.businesslogic.timeprovider.TimeProvider;
import com.example.timer.businesslogic.timeprovider.TimeProviderImpl;

/**
 * Created by ignacy on 03.01.18.
 */

public class TimeCounter {

	public TimeProvider provider = new TimeProviderImpl();
	long time1;
	long time2;
	long difference;

	public void startCounting() {
		time1 = provider.provideTime();
	}

	public long stopCounting() {
		time2 = provider.provideTime();
		return (time2 - time1);
	}

	public long provideDifference() {
		return (provider.provideTime()-time1);
	}


}
