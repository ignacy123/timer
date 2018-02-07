package com.example.timer.ui.main;

import android.arch.lifecycle.MutableLiveData;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.timer.R;
import com.example.timer.sql.ScoreDAO;
import com.example.timer.testing.SingleFragmentActivity;
import com.example.timer.util.AppExecutors;
import com.example.timer.util.TaskExecutorWithIdlingResourceRule;
import com.example.timer.util.TimeCounter;
import com.example.timer.util.ViewModelUtil;
import com.example.timer.viewmodel.MainViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ignacy on 09.11.17.
 */
@RunWith(AndroidJUnit4.class)
public class MainFragmentTest {

	@Rule
	public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class, true, true);

	@Rule
	public TaskExecutorWithIdlingResourceRule executorRule = new TaskExecutorWithIdlingResourceRule();

	private MainViewModel viewModel;
	private MutableLiveData<String> counter = new MutableLiveData<>();
	private MutableLiveData<String> scramble = new MutableLiveData<>();
	private TimeCounter timeCounter;
	private ScoreDAO scoreDAO;
	private AppExecutors executors;

	@Before
	public void init() {
		timeCounter = mock(TimeCounter.class);
		long a = 6000;
		when(timeCounter.provideDifference()).thenReturn(a);
		scoreDAO = mock(ScoreDAO.class);
		executors = mock(AppExecutors.class);
		MainFragment mainFragment = new MainFragment();
		viewModel = mock(MainViewModel.class);
		mainFragment.viewModelFactory = ViewModelUtil.createFor(viewModel);
		when(viewModel.getCounter()).thenReturn(counter);
		when(viewModel.getScramble()).thenReturn(scramble);
		activityRule.getActivity()
				.setFragment(mainFragment);
	}

	@Test
	public void showsData() {
		onView(ViewMatchers.withId(R.id.counter)).check(matches(withText("")));
	}

	@Test
	public void startsAndStopsCountingWhenClicked() {
		onView(withId(R.id.counter)).check(matches(withText("")));
		onView(withId(R.id.counter)).perform(click());
		verify(viewModel, times(1)).startCounting();
		onView(withId(R.id.counter)).perform(click());
		verify(viewModel, times(1)).stopCounting();
	}

	@Test
	public void showsCounterValue() {
		onView(withId(R.id.counter)).check(matches(withText("")));
		final String counterValue = "6.000";
		counter.postValue(counterValue);
		onView(withId(R.id.counter)).check(matches(withText(counterValue)));
	}

	@Test
	public void displaysScramble() {
		onView(withId(R.id.scramble)).check(matches(withText("")));
		final String scrambleValue = "Scramble will be displayed here.";
		scramble.postValue(scrambleValue);
		onView(withId(R.id.scramble)).check(matches(withText(scrambleValue)));
	}

	@Test
	public void generatesScramble() {
		onView(withId(R.id.scramble)).check(matches(withText("")));
		onView(withId(R.id.counter)).perform(click());
		verify(viewModel, times(1)).startCounting();
		onView(withId(R.id.counter)).perform(click());

	}

	//TODO
	@Test
	public void formatsTime() {
		viewModel = new MainViewModel(timeCounter, scoreDAO, executors);
		MainFragment mainFragment2 = new MainFragment();
		mainFragment2.viewModelFactory = ViewModelUtil.createFor(viewModel);
		activityRule.getActivity()
				.setFragment(mainFragment2);
		onView(withId(R.id.counter)).perform(click());
		onView(withId(R.id.counter)).perform(click());
		final String counterValue = "6.000";
		onView(withId(R.id.counter)).check(matches(withText(counterValue)));

	}

}
