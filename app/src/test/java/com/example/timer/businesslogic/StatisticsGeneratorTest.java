package com.example.timer.businesslogic;

import com.example.timer.businesslogic.timeprovider.StatisticsGenerator;
import com.example.timer.businesslogic.timeprovider.StatisticsGeneratorImpl;
import com.example.timer.businesslogic.timeprovider.TimeFormatterImpl;
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

	StatisticsGenerator generator = new StatisticsGeneratorImpl(new TimeFormatterImpl());
	List<Score> scores = new ArrayList<>();

	@Test
	public void countsAllKindsOfAverages() {
		Score dummyScore = new Score("", 1000, "");

		for (int i = 0; i <= 100; i++) {
			scores.add(dummyScore);
		}

		Statistics statistics = generator.generateAverages(scores);
		assertThat(statistics.getFormattedMo3(), is("1.000"));
		assertThat(statistics.getFormattedAvg5(), is("1.000"));
		assertThat(statistics.getFormattedAvg12(), is("1.000"));
		assertThat(statistics.getFormattedAvg50(), is("1.000"));
		assertThat(statistics.getFormattedAvg100(), is("1.000"));

	}

	@Test
	public void countsAverageEvenWhenOneTimeIsMissing() {
		scores.add(new Score("", 1000, ""));
		scores.add(new Score("", 1000, ""));
		scores.add(new Score("", 100, ""));
		scores.add(new Score("", 50, ""));

		Statistics statistics = generator.generateAverages(scores);
		assertThat(statistics.getFormattedMo3(), is("0.383"));
		assertThat(statistics.getFormattedAvg5(), is("0.700"));

	}

	@Test
	public void countsBestAverages() {
		scores.clear();
		Score dummyScore = new Score("", 1000, "");

		for (int i = 0; i <= 200; i++) {
			scores.add(dummyScore);
		}
		Statistics statistics = generator.generateAverages(scores);
		assertThat(statistics.getFormattedMo3(), is("1.000"));
		assertThat(statistics.getFormattedAvg5(), is("1.000"));
		assertThat(statistics.getFormattedAvg12(), is("1.000"));
		assertThat(statistics.getFormattedAvg50(), is("1.000"));
		assertThat(statistics.getFormattedAvg100(), is("1.000"));
	}

}
