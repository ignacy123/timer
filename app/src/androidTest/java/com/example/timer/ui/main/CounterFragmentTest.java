package com.example.timer.ui.main;

import android.arch.lifecycle.MutableLiveData;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.example.timer.R;
import com.example.timer.testing.SingleFragmentActivity;
import com.example.timer.util.TaskExecutorWithIdlingResourceRule;
import com.example.timer.util.ViewModelUtil;
import com.example.timer.viewmodel.CounterViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.*;

/**
 * Created by ignacy on 09.11.17.
 */
@RunWith(AndroidJUnit4.class)
public class CounterFragmentTest {

	@Rule
	public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class, true, true);

	@Rule
	public TaskExecutorWithIdlingResourceRule executorRule = new TaskExecutorWithIdlingResourceRule();

	private CounterViewModel viewModel;
	private MutableLiveData<String> counter = new MutableLiveData<>();
	private MutableLiveData<String> scramble = new MutableLiveData<>();

	@Before
	public void init() {
		CounterFragment counterFragment = new CounterFragment();
		viewModel = mock(CounterViewModel.class);
		counterFragment.viewModelFactory = ViewModelUtil.createFor(viewModel);
		when(viewModel.getCounter()).thenReturn(counter);
		when(viewModel.getScramble()).thenReturn(scramble);
		activityRule.getActivity()
				.setFragment(counterFragment);
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



}
