package com.example.timer.businesslogic;

import com.example.timer.businesslogic.timeprovider.TimeFormatter;
import com.example.timer.businesslogic.timeprovider.TimeFormatterImpl;
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
	public void formatsTime() {
		// TODO REVIEW to nie jest poprawny test jednostkowy
		// zaleznosci (provider i formatter) powinny byc mockami,
		// tutaj powinienes testować jedynie interakcje (czy sa wywolywane poprawne metody w zaleznosciach)
		// testy czy formatter poprawnie formatuje powinny byc w ramach osobnej klasy dotyczacej tylko formattera
		TimeProvider provider = new TimeProviderImpl();
		TimeFormatter formatter = new TimeFormatterImpl();
		TimeCounter counter = new TimeCounter(provider, formatter);
		long a = 6000;
		assertThat(counter.returnFormattedTime(a), is("6.000"));
		a = 65003;
		assertThat(counter.returnFormattedTime(a), is("1:05.003"));

	}

}
