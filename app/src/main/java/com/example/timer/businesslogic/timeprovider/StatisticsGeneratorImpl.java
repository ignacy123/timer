package com.example.timer.businesslogic.timeprovider;

import com.example.timer.model.Score;
import com.example.timer.model.Statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ignacy on 08.03.18.
 */

public class StatisticsGeneratorImpl implements StatisticsGenerator {

	private Statistics statistics = new Statistics();
	private List<Long> times = new ArrayList<>();

	@Override
	public Statistics generateAverages(List<Score> scores) {
		times = generateTimes(scores);
		statistics.setSingle(getSingle(times));
		statistics.setMo3(getMo3(times));
		statistics.setAvg5(countAverageOf(5));
		statistics.setAvg12(countAverageOf(12));
		statistics.setAvg50(countAverageOf(50));
		statistics.setAvg100(countAverageOf(100));
		return statistics;
	}

	private double getMo3(List<Long> times) {
		if (times.size() < 3) {
			return 0;
		}
		return (times.get(times.size() - 1) + times.get(times.size() - 2) + times.get(times.size() - 3)) / 3;
	}

	private List<Long> generateTimes(List<Score> scores) {
		for (Score score : scores) {
			times.add(score.getTime());
		}
		return times;
	}

	private double getSingle(List<Long> times) {
		return Collections.min(times);
	}

	private double countAverageOf(int length) {
		List<Long> avgTimes = new ArrayList<>();
		if (times.size() >= length - 1) {
			if (times.size() == length - 1) {
				for (int i = 0; i < length - 1; i++) {
					avgTimes.add(times.get(times.size() - i - 1));
				}
				avgTimes.remove(Collections.min(avgTimes));
				return countAverageOfAllElementsInList(avgTimes);
			}
			for (int i = 0; i < length; i++) {
				avgTimes.add(times.get(times.size() - i - 1));
			}
			removeMinAndMaxElementsFromLists(avgTimes);
		} else {
			return 0;
		}
		return countAverageOfAllElementsInList(avgTimes);

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
