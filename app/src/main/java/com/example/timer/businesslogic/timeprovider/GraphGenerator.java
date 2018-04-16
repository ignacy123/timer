package com.example.timer.businesslogic.timeprovider;

import com.example.timer.model.Statistics;
import com.jjoe64.graphview.GraphView;

import java.util.List;

/**
 * Created by ignacy on 16.04.18.
 */

public interface GraphGenerator {

	public void makeGraph(GraphView graph, List<Statistics> values);

}
