package com.example.timer.businesslogic;

import com.example.timer.businesslogic.timeprovider.TimeProvider;
import com.example.timer.businesslogic.timeprovider.TimeProviderImpl;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ignacy on 22.12.17.
 */

public class TimeProviderTest {

	@Test
	public void providesTime() {
		TimeProvider provider = new TimeProviderImpl();
		assertEquals(provider.provideTime(), System.currentTimeMillis());
	}

}
