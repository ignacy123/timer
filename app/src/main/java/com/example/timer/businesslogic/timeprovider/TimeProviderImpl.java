package com.example.timer.businesslogic.timeprovider;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ignacy on 22.12.17.
 */

@Singleton
public class TimeProviderImpl implements TimeProvider {

	@Inject
	public TimeProviderImpl() {

	}

	@Override
	public long provideTime() {
		return System.currentTimeMillis();
	}
}
