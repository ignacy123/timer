package com.example.timer.businesslogic.timeprovider;

import com.example.timer.model.Score;
import com.example.timer.model.Statistics;

import java.util.List;

/**
 * Created by ignacy on 08.03.18.
 */

public interface StatisticsGenerator {

	public Statistics generateAverages(List<Score> scores);

}
