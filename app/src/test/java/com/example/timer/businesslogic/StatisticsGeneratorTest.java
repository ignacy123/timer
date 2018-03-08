package com.example.timer.businesslogic;

import com.example.timer.businesslogic.timeprovider.StatisticsGenerator;
import com.example.timer.businesslogic.timeprovider.StatisticsGeneratorImpl;
import com.example.timer.model.Score;
import com.example.timer.model.Statistics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by ignacy on 08.03.18.
 */

public class StatisticsGeneratorTest {

	StatisticsGenerator generator = new StatisticsGeneratorImpl();
	List<Score> scores = new ArrayList<>();

	@Test
	public void countsAllKindsAverages() {
		Score dummyScore = new Score("", 1000, "");

		for (int i = 0; i <= 200; i++) {
			scores.add(dummyScore);
		}

		Statistics statistics = generator.generateAverages(scores);
		assertThat(statistics.getMo3(), is(1000.0));
		assertThat(statistics.getAvg5(), is(1000.0));
		assertThat(statistics.getAvg12(), is(1000.0));
		assertThat(statistics.getAvg50(), is(1000.0));
		assertThat(statistics.getAvg100(), is(1000.0));

	}

	@Test
	public void countsAverageEvenWhenOneTimeIsMissing() {
		scores.add(new Score("", 1000, ""));
		scores.add(new Score("", 1000, ""));
		scores.add(new Score("", 100, ""));
		scores.add(new Score("", 50, ""));

		Statistics statistics = generator.generateAverages(scores);
		assertThat(statistics.getMo3(), is(383.0));
		assertThat(statistics.getAvg5(), is(700.0));

	}

}
