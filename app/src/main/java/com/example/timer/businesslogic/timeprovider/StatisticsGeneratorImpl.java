package com.example.timer.businesslogic.timeprovider;

import com.example.timer.model.Score;
import com.example.timer.model.Statistics;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ignacy on 08.03.18.
 */
@Singleton
public class StatisticsGeneratorImpl implements StatisticsGenerator {

	private Statistics statistics = new Statistics();
	private List<Long> times = new ArrayList<>();

	@Inject
	public StatisticsGeneratorImpl() {
	}

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
		// TODO REVIEW - poniższe mógłbyś zapisać inaczej. Zastanów się jak. Na razie nie podpowiadam.
		return (times.get(times.size() - 1) + times.get(times.size() - 2) + times.get(times.size() - 3)) / 3;
	}

	private List<Long> generateTimes(List<Score> scores) {
		for (Score score : scores) {
			times.add(score.getTime());
		}
		return times;
	}

	private double getSingle(List<Long> times) {
		// TODO REVIEW - appka mi się crashuje od razu po wejściu, nie mam żadnych wyników, pewnie dlatego
		//		java.util.NoSuchElementException
		//		at java.util.ArrayList$ArrayListIterator.next(ArrayList.java:576)
		//		at java.util.Collections.min(Collections.java:1654)
		//		at com.example.timer.businesslogic.timeprovider.StatisticsGeneratorImpl.getSingle(StatisticsGeneratorImpl.java:53)
		//		at com.example.timer.businesslogic.timeprovider.StatisticsGeneratorImpl.generateAverages(StatisticsGeneratorImpl.java:28)
		//		at com.example.timer.viewmodel.CounterViewModel.updateAverages(CounterViewModel.java:108)
		//		at com.example.timer.ui.main.CounterFragment.lambda$onActivityCreated$7$CounterFragment(CounterFragment.java:77)
		//		at com.example.timer.ui.main.CounterFragment$$Lambda$7.onChanged(Unknown Source)

		return Collections.min(times);
	}

	private double countAverageOf(int length) {
		List<Long> avgTimes = new ArrayList<>();
		// TODO REVIEW - Ciężko mi się to czyta. Wydaje mi się, że też da się to trochę ładniej zapisać.
		// Ale najpierw zastanów się nad tym z getMo3
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
