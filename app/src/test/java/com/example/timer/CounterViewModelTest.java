package com.example.timer;

/**
 * Created by ignacy on 23.11.17.
 */

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import com.example.timer.sql.ScoreDAO;
import com.example.timer.util.AppExecutors;
import com.example.timer.util.TimeCounter;
import com.example.timer.viewmodel.CounterViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by ignacy on 18.10.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class CounterViewModelTest {

	private CounterViewModel viewModel;
	private TimeCounter timeCounter;
	private ScoreDAO scoreDAO;
	private AppExecutors executors;

	@Before
	public void setUp() {
		timeCounter = mock(TimeCounter.class);
		scoreDAO = mock(ScoreDAO.class);
		executors = mock(AppExecutors.class);
		viewModel = new CounterViewModel(timeCounter, scoreDAO, executors);
	}

	@Test
	public void assertReturnsCounter() {
		viewModel.startCounting();
		assertThat(viewModel.getCounter()
				.getValue()).isNull();
	}

	@Rule
	public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();
}

