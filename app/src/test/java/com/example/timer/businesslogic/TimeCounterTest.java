package com.example.timer.businesslogic;

import com.example.timer.businesslogic.timeprovider.TimeFormatter;
import com.example.timer.businesslogic.timeprovider.TimeProvider;
import com.example.timer.util.TimeCounter;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by ignacy on 08.02.18.
 */

public class TimeCounterTest {

	@Test
	public void formatsTime() {
		TimeProvider provider = Mockito.mock(TimeProvider.class);
		TimeFormatter formatter = Mockito.mock(TimeFormatter.class);
		TimeCounter counter = new TimeCounter(provider, formatter);
		long a = 6000;
		counter.returnFormattedTime(a);
		verify(formatter, times(1)).formatTime(a);

	}

}
