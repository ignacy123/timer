package com.example.timer.businesslogic.timeprovider;

import com.example.timer.model.Score;
import com.example.timer.model.Statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ignacy on 08.03.18.
 */
@Singleton
public class StatisticsGeneratorImpl implements StatisticsGenerator {

	private List<Long> times = new ArrayList<>();
	TimeFormatter formatter;

	@Inject
	public StatisticsGeneratorImpl(TimeFormatter formatter) {
		this.formatter = formatter;
	}

	@Override
	public Statistics generateAverages(List<Score> scores) {
		times = generateTimes(scores);
		//@formatter:off
		Statistics statistics = new Statistics(
				formatter.formatTime(getSingle(times)),
				formatter.formatTime(getMo3(times)),
				formatter.formatTime(countAverageOf(5)),
				formatter.formatTime(countAverageOf(12)),
				formatter.formatTime(countAverageOf(50)),
				formatter.formatTime(countAverageOf(100)),
				getSingle(times),
				getMo3(times),
				countAverageOf(5),
				countAverageOf(12),
				countAverageOf(50),
				countAverageOf(100)
				);
		//@formatter:on

		return statistics;
	}

	private long getMo3(List<Long> times) {
		if (times.size() < 3) {
			return 0;
		}
		return countAverageOfAllElementsInList(times.subList(times.size() - 3, times.size()), 0L, 0);
	}

	private List<Long> generateTimes(List<Score> scores) {
		times.clear();
		for (Score score : scores) {
			times.add(score.getTime());
		}
		return times;
	}

	private long getSingle(List<Long> times) {
		if (times.size() == 0) {
			return 0;
		}
		return times.get(times.size() - 1);

	}

	private long countAverageOf(int length) {
		List<Long> avgTimes = new ArrayList<>();
		if (times.size() == length - 1) {
			avgTimes = times.subList(times.size() - length + 1, times.size());
			return countAverageOfAllElementsInList(avgTimes, Collections.min(avgTimes), 1);
		} else if (times.size() > length - 1) {
			avgTimes = times.subList(times.size() - length, times.size());
			return countAverageOfAllElementsInList(avgTimes, sumOfMinAndMax(avgTimes), 2);

		} else {
			return 0;
		}

	}

	private long countAverageOfAllElementsInList(List<Long> times, Long aLong, int i) {
		long sum = 0;
		for (Long time : times) {
			sum += time;
		}
		return (sum - aLong) / (times.size() - i);
	}

	private Long sumOfMinAndMax(List<Long> timesToRemove) {
		return Collections.min(timesToRemove) + Collections.max(timesToRemove);
	}
}
