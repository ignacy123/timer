package com.example.timer.businesslogic.timeprovider;

import com.example.timer.model.Statistics;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

/**
 * Created by ignacy on 16.04.18.
 */

public class GraphGeneratorImpl implements GraphGenerator {

	@Override
	public void makeGraph(GraphView graph, List<Statistics> values) {

		LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] { new DataPoint(0, values.get(0)
				.getSingle()), new DataPoint(1, values.get(1)
				.getSingle()), });
		series.setAnimated(true);
		graph.addSeries(series);
	}
}
