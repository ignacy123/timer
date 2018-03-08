package com.example.timer.businesslogic.timeprovider;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ignacy on 07.03.18.
 */

@Singleton
public class TimeFormatterImpl implements TimeFormatter {

	@Inject
	public TimeFormatterImpl() {

	}


	public String chooseTimeFormat(long timeToFormat) {
		if (timeToFormat < 10000) {
			return formatTime("s.SSS", timeToFormat);
		}
		if (timeToFormat < 60000) {
			return formatTime("ss.SSS", timeToFormat);
		}
		return formatTime("m:ss.SSS", timeToFormat);
	}

	public String formatTime(String pattern, long timeToFormat){
		return new SimpleDateFormat(pattern).format(new Date(timeToFormat));
	}
}