package com.example.timer;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.timer.util.FakeTimeProvider;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by ignacy on 09.11.17.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

	private FakeTimeProvider fakeTimeProvider = new FakeTimeProvider();

	@Before
	public void init() {
		activityRule.getActivity().viewModel.provider = fakeTimeProvider;

	}

	@Rule
	public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class, true, true);

	@Test
	public void showsData() {
		onView(withId(R.id.counter)).check(matches(withText("")));
	}

	@Test
	public void startsCountingWhenClicked() {
		onView(withId(R.id.counter)).check(matches(withText("")));
		onView(withId(R.id.counter)).perform(click());
		onView(withId(R.id.counter)).check(matches(withText("counting")));
	}

	@Test
	public void countsTime() {
		onView(withId(R.id.counter)).check(matches(withText("")));
		fakeTimeProvider.setTime(0);
		onView(withId(R.id.counter)).perform(click());
		fakeTimeProvider.setTime(6000);
		onView(withId(R.id.counter)).perform(click());

		onView(withId(R.id.counter)).check(matches(withText("6000")));
	}

	@Test
	public void displaysScramble() {
		onView(withId(R.id.scramble)).check(matches(withText("Scramble will be displayed here.")));
	}

}
