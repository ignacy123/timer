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

	public String formatTime(long timeToFormat) {
		return new SimpleDateFormat(generatePattern(timeToFormat)).format(new Date(timeToFormat));
	}

	private String generatePattern(long timeToFormat) {
		if (timeToFormat < 10000) {
			return "s.SSS";
		}
		if (timeToFormat < 60000) {
			return "ss.SSS";
		}
		return "m:ss.SSS";
	}

}