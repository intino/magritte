package tara.intellij.codeinsight.intentions.chart;

import com.intellij.openapi.diagnostic.Logger;
import org.jetbrains.annotations.NotNull;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import tara.lang.model.Node;
import tara.lang.model.Parameter;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

import static java.time.format.DateTimeFormatter.ofPattern;

public class DatasetCreator {
	private static final Logger LOG = Logger.getInstance(DatasetCreator.class.getName());

	private DatasetCreator() {
	}

	static XYDataset createDataset(List<Node> nodes, String x, String y) {
		List<AbstractMap.SimpleEntry<Object, Double>> data = collectData(nodes, x, y);
		if (!data.isEmpty()) return fillDataSet(x, y, data);
		else {
			DefaultXYDataset ds = new DefaultXYDataset();
			ds.addSeries("No representable values", new double[][]{{0}, {0}});
			return ds;
		}
	}

	@NotNull
	private static List<AbstractMap.SimpleEntry<Object, Double>> collectData(List<Node> nodes, String x, String y) {
		List<AbstractMap.SimpleEntry<Object, Double>> data = new ArrayList<>();
		for (Node node : nodes) {
			final Parameter xParameter = findParameter(node, x);
			final Parameter yParameter = findParameter(node, y);
			if (isCompatible(xParameter) && isCompatible(yParameter))
				data.add(new AbstractMap.SimpleEntry<>(asCompatibleValue(xParameter), (Double) yParameter.values().get(0)));
		}
		return data;
	}

	@NotNull
	private static XYDataset fillDataSet(String x, String y, List<AbstractMap.SimpleEntry<Object, Double>> data) {
		final boolean timeSeries = isTimeSerie(data);
		if (timeSeries) {
			TimeSeriesCollection ds = new TimeSeriesCollection();
			ds.addSeries(toTimeSeries(data, x, y));
			return ds;
		} else {
			DefaultXYDataset ds = new DefaultXYDataset();
			ds.addSeries(x + " / " + y, toDoubleMatrix(data));
			return ds;
		}
	}

	private static boolean isTimeSerie(List<AbstractMap.SimpleEntry<Object, Double>> data) {
		return !(data.get(0).getKey() instanceof Double);
	}

	private static boolean isCompatible(Parameter parameter) {
		return parameter != null && parameter.values().size() == 1 && (parameter.values().get(0) instanceof Double || parseAsDate(parameter) != null);
	}

	private static Object asCompatibleValue(Parameter parameter) {
		if (parameter.values().get(0) instanceof Double)
			return parameter.values().get(0);
		else {
			LocalDateTime dateTime = parseAsDate(parameter);
			if (dateTime == null) return null;
			return new Second(dateTime.getSecond(), dateTime.getMinute(), dateTime.getHour(), dateTime.getDayOfMonth(), dateTime.getMonthValue(), dateTime.getYear());
		}
	}

	private static LocalDateTime parseAsDate(Parameter parameter) {
		try {
			final Object o = parameter.values().get(0);
			if (o == null) return null;
			return LocalDateTime.parse(o.toString().replace("\"", ""), ofPattern("dd/MM/yyyy HH:mm:ss"));
		} catch (DateTimeParseException e) {
			LOG.info(e.getMessage(), e);
			return null;
		}
	}

	private static double[][] toDoubleMatrix(List<AbstractMap.SimpleEntry<Object, Double>> data) {
		double[][] doubles = new double[2][data.size()];
		for (int i = 0; i < data.size(); i++) {
			doubles[0][i] = (double) data.get(i).getKey();
			doubles[1][i] = data.get(i).getValue();
		}
		return doubles;
	}

	private static TimeSeries toTimeSeries(List<AbstractMap.SimpleEntry<Object, Double>> data, String x, String y) {
		final TimeSeries timeSeries = new TimeSeries(y + " / " + x);
		try {
			for (AbstractMap.SimpleEntry<Object, Double> entry : data)
				timeSeries.add((RegularTimePeriod) entry.getKey(), entry.getValue());
		} catch (SeriesException e) {
			LOG.info(e.getMessage(), e);
		}
		return timeSeries;
	}

	private static Parameter findParameter(Node node, String name) {
		for (Parameter parameter : node.parameters())
			if (parameter.name().equals(name)) return parameter;
		return null;
	}
}
