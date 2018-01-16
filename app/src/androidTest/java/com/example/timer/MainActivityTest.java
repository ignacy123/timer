package com.example.timer;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.timer.util.ViewModelUtil;
import com.example.timer.viewmodel.MainActivityViewModel;

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

/**
 * Created by ignacy on 09.11.17.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

	private MainActivityViewModel viewModel;

	@Before

	public void init() {
		viewModel = mock(MainActivityViewModel.class);
		activityRule.getActivity().viewModelFactory = ViewModelUtil.createFor(viewModel);
		activityRule.launchActivity(null);
	}

	@Rule
	public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class, true, false);

	@Test
	public void showsData() {
		onView(withId(R.id.counter)).check(matches(withText("")));
	}

	@Test
	public void startsCountingWhenClicked() {
		onView(withId(R.id.counter)).check(matches(withText("")));
		onView(withId(R.id.counter)).perform(click());
		onView(withId(R.id.counter)).check(matches(withText("")));//TODO checks if counter value varies from ""
	}

	@Test
	public void countsTime() {
		onView(withId(R.id.counter)).check(matches(withText("")));
		onView(withId(R.id.counter)).perform(click());
		verify(viewModel, times(1)).startCounting();
		onView(withId(R.id.counter)).perform(click());
		verify(viewModel, times(1)).stopCounting();

		//		onView(withId(R.id.counter)).check(matches(withText("6000")));
	}

	@Test
	public void displaysScramble() {
		onView(withId(R.id.scramble)).check(matches(withText("Scramble will be displayed here.")));
	}

}
