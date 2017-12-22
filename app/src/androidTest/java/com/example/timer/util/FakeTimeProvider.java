package com.example.timer.util;

import com.example.timer.businesslogic.timeprovider.TimeProvider;

/**
 * Created by ignacy on 22.12.17.
 */

public class FakeTimeProvider implements TimeProvider {

	private long time;

	@Override
	public long provideTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
}
