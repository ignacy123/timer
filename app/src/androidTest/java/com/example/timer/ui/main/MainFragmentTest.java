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

import java.util.concurrent.Executor;

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
public class MainFragmentTest {

	@Rule
	public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class, true, true);

	@Rule
	public TaskExecutorWithIdlingResourceRule executorRule = new TaskExecutorWithIdlingResourceRule();

	private MainViewModel viewModel;
	private MutableLiveData<String> counter = new MutableLiveData<>();
	private MutableLiveData<String> scramble = new MutableLiveData<>();

	@Before
	public void init() {
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

	//TODO doprawadziłem ten test do działania ale raczej nie to chciałeś tu przetestować
	// Poniższe nie miało za bardzo sensu:
	// when(timeCounter.provideDifference()).thenReturn(a);
	// W tym co miałeś wcześniej metoda provideDifference nie byłaby nigdy wywołana bo zamockowałeś TimeCounter,
	// a poniewaz to mock, więc nie będzie wywołania startCounting.
	//
	// Z kolei w samym teście miałes dwa clicki na counterze, a w stopCounting jest wywoływane getFormattedTime,
	// więc to wywołanie metody "nagrałem" w mocku. Tylko po nazwie metody zakładam, że to nie to co chciałeś przetestować.
	//
	// A tak na prawdę w tej warstwie testów zależysz tylko od zamockowanego ViewModelu, i takie zachowanie jest porządane.
	// Dzięki temu nie zależysz od szczegółów implementacyjnych. Jeśli chcesz przetestować czy Timer formatuje to powinno się
	// to odbyć w testach jednostkowych a nie w testach instrumentacyjnych widoku.
	//
	@Test
	public void formatsTime() {
		final String counterValue = "6.000";
		TimeCounter timeCounter = mock(TimeCounter.class);
		when(timeCounter.getFormattedTime()).thenReturn(counterValue);
		ScoreDAO scoreDAO = mock(ScoreDAO.class);
		AppExecutors executors = mock(AppExecutors.class);
		when(executors.diskIO()).thenReturn(mock(Executor.class));

		viewModel = new MainViewModel(timeCounter, scoreDAO, executors);
		MainFragment mainFragment2 = new MainFragment();
		mainFragment2.viewModelFactory = ViewModelUtil.createFor(viewModel);
		activityRule.getActivity()
				.replaceFragment(mainFragment2);
		onView(withId(R.id.counter)).perform(click());
		onView(withId(R.id.counter)).perform(click());
		onView(withId(R.id.counter)).check(matches(withText(counterValue)));

	}

}
