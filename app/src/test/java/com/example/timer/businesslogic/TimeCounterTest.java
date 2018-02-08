package com.example.timer.businesslogic;

import com.example.timer.businesslogic.timeprovider.TimeProvider;
import com.example.timer.businesslogic.timeprovider.TimeProviderImpl;
import com.example.timer.util.TimeCounter;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by ignacy on 08.02.18.
 */

public class TimeCounterTest {

	@Test
	public void formatsTime(){
		TimeProvider provider = new TimeProviderImpl();
		TimeCounter counter = new TimeCounter(provider);
		long a = 6000;
		assertThat(counter.returnFormattedTime(a), is("6.000"));
		a = 65003;
		assertThat(counter.returnFormattedTime(a), is("1:5.003"));

	}

}
