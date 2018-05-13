package com.example.timer.businesslogic.timeprovider;

import com.example.timer.model.Score;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ignacy on 16.04.18.
 */

public class GraphGeneratorImpl implements GraphGenerator {

	private final TimeFormatter formatter;

	@Inject
	public GraphGeneratorImpl(TimeFormatter formatter) {
		this.formatter = formatter;
	}

	@Override
	public void makeGraph(GraphView graph, List<Score> values) {

		DataPoint[] dataPoints;
		dataPoints = new DataPoint[values.size()];
		for (int i = 0; i < values.size(); i++) {
			dataPoints[i] = new DataPoint(i, values.get(i)
					.getTime());
		}

		LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoints);
		series.setDrawDataPoints(true);
		series.setDataPointsRadius(10f);

		graph.getViewport()
				.setXAxisBoundsManual(true);
		graph.getViewport()
				.setMaxX(values.size());
		graph.getGridLabelRenderer()
				.setLabelFormatter(new DefaultLabelFormatter() {

					@Override
					public String formatLabel(double value, boolean isValueX) {
						if (isValueX) {
							// show normal x values
							return super.formatLabel(value, isValueX);
						} else {
							// show currency for y values
							return formatter.formatTime((long) value);
						}
					}
				});

		graph.addSeries(series);
	}
}
