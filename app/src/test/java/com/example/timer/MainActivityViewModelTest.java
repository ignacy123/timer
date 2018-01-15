package com.example.timer;

/**
 * Created by ignacy on 23.11.17.
 */

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.timer.viewmodel.MainActivityViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by ignacy on 18.10.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainActivityViewModelTest {

	MainActivityViewModel viewModel;

	@Before
	public void setUp() {
		viewModel = new MainActivityViewModel();//TODO - w starym projekcie tutaj jest użyty servicelocator. W tym projekcie zrezygnowaliśmy z niego na rzecz Daggera.
		//Czy tu po implementecji daggera będzie dało się poprawić ten test?
	}

	@Test
	public void assertReturnsCounter() {
		viewModel.startCounting();
		assertThat(viewModel.getCounter()
				.getValue(), is("counting"));
	}

	@Rule
	public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();
}

