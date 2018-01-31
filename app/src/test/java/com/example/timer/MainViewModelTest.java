package com.example.timer;

/**
 * Created by ignacy on 23.11.17.
 */

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import com.example.timer.sql.ScoreDAO;
import com.example.timer.util.AppExecutors;
import com.example.timer.util.TimeCounter;
import com.example.timer.viewmodel.MainViewModel;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by ignacy on 18.10.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainViewModelTest {

	private MainViewModel viewModel;
	private TimeCounter timeCounter;
	private ScoreDAO scoreDAO;
	private AppExecutors executors;

	@Before
	public void setUp() {
		timeCounter = mock(TimeCounter.class);
		scoreDAO = mock(ScoreDAO.class);
		executors = mock(AppExecutors.class);
		viewModel = new MainViewModel(timeCounter, scoreDAO, executors);
	}

	@Test
	public void assertReturnsCounter() {
		viewModel.startCounting();
		assertThat(viewModel.getCounter()
				.getValue(), is(nullValue()));
	}

	@Rule
	public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();
}

