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

	private Statistics statistics = new Statistics();
	private List<Long> times = new ArrayList<>();
	TimeFormatter formatter;

	@Inject
	public StatisticsGeneratorImpl(TimeFormatter formatter) {
		this.formatter = formatter;
	}

	@Override
	public Statistics generateAverages(List<Score> scores) {
		times = generateTimes(scores);
		statistics.setSingle(formatter.formatTime((long) getSingle(times)));
		statistics.setMo3(formatter.formatTime((long) getMo3(times)));
		statistics.setAvg5(formatter.formatTime((long) countAverageOf(5)));
		statistics.setAvg12(formatter.formatTime((long) countAverageOf(12)));
		statistics.setAvg50(formatter.formatTime((long) countAverageOf(50)));
		statistics.setAvg100(formatter.formatTime((long) countAverageOf(100)));
		return statistics;
	}

	private double getMo3(List<Long> times) {
		if (times.size() < 3) {
			return 0;
		}
		return countAverageOfAllElementsInList(times.subList(times.size() - 3, times.size()));
	}

	private List<Long> generateTimes(List<Score> scores) {
		times.clear();
		for (Score score : scores) {
			times.add(score.getTime());
		}
		return times;
	}

	private double getSingle(List<Long> times) {
		if (times.size() == 0) {
			return 0;
		}
		return Collections.min(times);

	}

	private double countAverageOf(int length) {
		List<Long> avgTimes = new ArrayList<>();
		if (times.size() == length - 1) {
			avgTimes = times.subList(times.size() - length + 1, times.size());
			avgTimes.remove(Collections.min(avgTimes));
			return countAverageOfAllElementsInList(avgTimes);
		} else if (times.size() > length - 1) {
			avgTimes = times.subList(times.size() - length, times.size());
			removeMinAndMaxElementsFromLists(avgTimes);
			return countAverageOfAllElementsInList(avgTimes);

		} else {
			return 0;
		}

	}

	private double countAverageOfAllElementsInList(List<Long> times) {
		long sum = 0;
		for (Long time : times) {
			sum += time;
		}
		return sum / times.size();
	}

	private List<Long> removeMinAndMaxElementsFromLists(List<Long> times) {
		times.remove(Collections.min(times));
		times.remove(Collections.max(times));
		return times;
	}
}
