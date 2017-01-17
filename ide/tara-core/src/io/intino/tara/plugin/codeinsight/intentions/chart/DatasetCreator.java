package io.intino.tara.plugin.codeinsight.intentions.chart;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiFile;
import com.opencsv.CSVReader;
import org.jetbrains.annotations.NotNull;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;
import static java.time.format.DateTimeFormatter.ofPattern;

public class DatasetCreator {
	private static final Logger LOG = Logger.getInstance(DatasetCreator.class.getName());
	private List<String[]> data;
	private List<String> header;

	DatasetCreator(PsiFile dataFile) {
		try {
			CSVReader reader = new CSVReader(new FileReader(dataFile.getVirtualFile().getPath()), ';', '"');
			final List<String[]> rawData = reader.readAll();
			this.data = rawData.subList(1, rawData.size());
			header = Arrays.asList(rawData.get(0)).stream().map(String::trim).collect(Collectors.toList());
			cleanData();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	XYDataset createDataset(int x, int y, int drill) {
		if (!data.isEmpty()) return build(x, y, drill);
		else {
			DefaultXYDataset ds = new DefaultXYDataset();
			ds.addSeries("No representable values", new double[][]{{0}, {0}});
			return ds;
		}
	}

	List<String> header() {
		return header;
	}

	@NotNull
	private XYDataset build(int x, int y, int drill) {
		if (isTimeSeries()) {
			TimeSeriesCollection ds = new TimeSeriesCollection();
//			ds.addSeries(toTimeSeries(y));
			return ds;
		} else {
			DefaultXYDataset ds = new DefaultXYDataset();
			if (drill < 0) ds.addSeries(header().get(x) + " / " + header().get(y), toDoubleMatrix(x, y));
			else {
				Map<Double, double[][]> drilledMatrix = drillMatrix(x, y, drill);
				drilledMatrix.forEach((e, v) -> ds.addSeries(header().get(drill) + " = " + e, v));
			}
			return ds;
		}
	}

	private Map<Double, double[][]> drillMatrix(int x, int y, int drill) {
		Map<Double, double[][]> map = new HashMap<>();
		final Set<Double> values = data.stream().map(v -> parseDouble(v[drill])).collect(Collectors.toSet());
		for (Double value : values)
			map.put(value, filteredMatrix(x, y, drill, value));
		return map;
	}

	private double[][] toDoubleMatrix(int x, int y) {
		double[][] values = new double[2][data.size()];
		for (int i = 0; i < data.size(); i++) {
			values[0][i] = parseDouble(data.get(i)[x].trim());
			values[1][i] = parseDouble(data.get(i)[y].trim());
		}
		return values;
	}

	private double[][] filteredMatrix(int x, int y, int z, double zvalue) {
		double[][] values = new double[2][sizeOf(z, zvalue)];
		final int[] i = {0};
		data.forEach(v -> {
			if (parseDouble(v[z]) == zvalue) {
				values[0][i[0]] = parseDouble(v[x]);
				values[1][i[0]] = parseDouble(v[y]);
				i[0]++;
			}
		});
		return values;
	}

	private int sizeOf(int z, double zvalue) {
		return (int) data.stream().filter(v -> parseDouble(v[z]) == zvalue).count();
	}

	private Second parseAsDate(Object o) {
		try {
			if (o == null) return null;
			final LocalDateTime dateTime = LocalDateTime.parse(o.toString().replace("\"", ""), ofPattern("dd/MM/yyyy HH:mm:ss"));
			if (dateTime == null) return null;
			return new Second(dateTime.getSecond(), dateTime.getMinute(), dateTime.getHour(), dateTime.getDayOfMonth(), dateTime.getMonthValue(), dateTime.getYear());
		} catch (DateTimeParseException e) {
			LOG.info(e.getMessage(), e);
			return null;
		}
	}

	private boolean isTimeSeries() {
		return parseAsDate(data.get(1)[0]) != null;
	}

	private TimeSeries toTimeSeries(String y) {
		final TimeSeries timeSeries = new TimeSeries(y + " / " + data.get(0)[0]);
		String[] xSerie = extractColumn(0);
		String[] ySerie = extractColumn(Arrays.asList(data.get(0)).indexOf(y));
		try {
			for (int i = 1; i < xSerie.length; i++) {
				timeSeries.add(parseAsDate(xSerie[i]), parseDouble(ySerie[i]));
			}
		} catch (SeriesException e) {
			LOG.info(e.getMessage(), e);
		}
		return timeSeries;
	}

	private String[] extractColumn(int i) {
		List<String> arrayList = data.stream().map(row -> row[i]).collect(Collectors.toList());
		return arrayList.toArray(new String[arrayList.size()]);
	}

	private void cleanData() {
		data.removeAll(data.stream().filter(this::isEmpty).collect(Collectors.toList()));
	}

	public boolean isEmpty(String[] data) {
		return data.length == 0 || data.length == 1 && data[0].trim().isEmpty();
	}
}
