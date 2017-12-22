package com.example.timer.businesslogic.timeprovider;

/**
 * Created by ignacy on 22.12.17.
 */

public class TimeProviderImpl implements TimeProvider {

	@Override
	public long provideTime() {
		return System.currentTimeMillis();
	}
}
