package com.example.timer.db;

import android.support.test.runner.AndroidJUnit4;

import com.example.timer.model.Statistics;

import org.junit.Test;
import org.junit.runner.RunWith;

import static com.example.timer.util.LiveDataTestUtil.getValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by ignacy on 28.03.18.
 */
@RunWith(AndroidJUnit4.class)
public class StatisticsDaoTest extends DbTest {

	@Test
	public void findsBestSingle() throws InterruptedException {
		db.statisticsDao()
				.getBestSingle();
		Statistics statistics = new Statistics("", "", "", "", "", "", 500, 0, 0, 0, 0, 0);
		Statistics statistics2 = new Statistics("", "", "", "", "", "", 600, 0, 0, 0, 0, 0);
		db.statisticsDao()
				.persist(statistics);
		db.statisticsDao()
				.persist(statistics2);
		assertThat(getValue(db.statisticsDao()
				.getBestSingle()), is(500L));
	}
}
