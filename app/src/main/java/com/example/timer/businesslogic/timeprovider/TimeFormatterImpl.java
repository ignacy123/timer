package com.example.timer.businesslogic.timeprovider;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ignacy on 07.03.18.
 */

@Singleton
public class TimeFormatterImpl implements TimeFormatter {

	@Inject
	public TimeFormatterImpl() {

	}

	@Override
	public String formatTime(long timeToFormat) {
		if (timeToFormat < 10000) {
			return (new SimpleDateFormat("s.SSS")).format(new Date(timeToFormat));
		}
		if (timeToFormat < 60000) {
			return (new SimpleDateFormat("ss.SSS")).format(new Date(timeToFormat));
		}
		return (new SimpleDateFormat("m:ss.SSS")).format(new Date(timeToFormat));
	}
}