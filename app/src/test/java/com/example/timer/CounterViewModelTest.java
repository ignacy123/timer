package com.example.timer;

/**
 * Created by ignacy on 23.11.17.
 */

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.timer.businesslogic.timeprovider.StatisticsGenerator;
import com.example.timer.sql.ScoreDao;
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
	private ScoreDao scoreDao;
	private AppExecutors executors;
	private StatisticsGenerator statisticsGenerator;

	@Before
	public void setUp() {
		timeCounter = mock(TimeCounter.class);
		scoreDao = mock(ScoreDao.class);
		executors = mock(AppExecutors.class);
		statisticsGenerator = mock(StatisticsGenerator.class);
		viewModel = new CounterViewModel(timeCounter, scoreDao, executors, statisticsGenerator);
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

