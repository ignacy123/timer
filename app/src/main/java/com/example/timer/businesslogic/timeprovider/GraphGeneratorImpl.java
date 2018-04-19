package com.example.timer.businesslogic.timeprovider;

import com.example.timer.model.Statistics;
import com.jjoe64.graphview.DefaultLabelFormatter;
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

		DataPoint[] dataPoints;
		dataPoints = new DataPoint[values.size()];
		for (int i = 0; i < values.size(); i++) {
			dataPoints[i] = new DataPoint(i, values.get(i)
					.getSingle());
		}

		LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoints);
		series.setDrawDataPoints(true);
		series.setDataPointsRadius(10f);
		series.setAnimated(true);
		graph.addSeries(series);
		graph.getGridLabelRenderer()
				.setLabelFormatter(new DefaultLabelFormatter() {

					@Override
					public String formatLabel(double value, boolean isValueX) {
						if (isValueX) {
							// show normal x values
							return super.formatLabel(value, isValueX);
						} else {
							// show currency for y values
							TimeFormatter formatter =
									new TimeFormatterImpl(); // TODO próbowałem wstrzyknąć daggerem, ale coś nie pykło, dlatego jest tutaj
							return formatter.formatTime((long) value);
						}
					}
				});
	}
}
