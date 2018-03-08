package com.example.timer.businesslogic;

import com.example.timer.businesslogic.timeprovider.TimeFormatter;
import com.example.timer.businesslogic.timeprovider.TimeFormatterImpl;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by ignacy on 08.03.18.
 */

public class TestFormatterTest {

	@Test
	public void formatsTime() {
		TimeFormatter formatter = new TimeFormatterImpl();
		long a = 6000;
		assertThat(formatter.chooseTimeFormat(a), is("6.000"));
		a = 65003;
		assertThat(formatter.chooseTimeFormat(a), is("1:05.003"));

	}

}
