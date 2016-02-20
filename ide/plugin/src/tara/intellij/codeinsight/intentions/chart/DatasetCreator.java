package tara.intellij.codeinsight.intentions.chart;

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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.format.DateTimeFormatter.ofPattern;

public class DatasetCreator {
	private static final Logger LOG = Logger.getInstance(DatasetCreator.class.getName());
	private List<String[]> data;

	DatasetCreator(PsiFile dataFile) {
		try {
			CSVReader reader = new CSVReader(new FileReader(dataFile.getVirtualFile().getPath()), ';', '"');
			data = reader.readAll();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	XYDataset createDataset(String y) {
		if (!data.isEmpty()) return build(y);
		else {
			DefaultXYDataset ds = new DefaultXYDataset();
			ds.addSeries("No representable values", new double[][]{{0}, {0}});
			return ds;
		}
	}

	String[] header() {
		return data.get(0);
	}

	@NotNull
	private XYDataset build(String y) {
		if (isTimeSerie()) {
			TimeSeriesCollection ds = new TimeSeriesCollection();
			ds.addSeries(toTimeSeries(y));
			return ds;
		} else {
			DefaultXYDataset ds = new DefaultXYDataset();
			ds.addSeries(data.get(0)[0] + " / " + y, toDoubleMatrix(y));
			return ds;
		}
	}

	private boolean isTimeSerie() {
		return parseAsDate(data.get(1)[0]) != null;
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

	private double[][] toDoubleMatrix(String y) {
		double[][] doubles = new double[2][data.size()];
		for (int i = 1; i < data.size(); i++) {
			doubles[0][i] = Double.parseDouble(data.get(i)[0]);
			doubles[1][i] = Double.parseDouble(data.get(i)[Arrays.asList(header()).indexOf(y)]);
		}
		return doubles;
	}

	private TimeSeries toTimeSeries(String y) {
		final TimeSeries timeSeries = new TimeSeries(y + " / " + data.get(0)[0]);
		String[] xSerie = extractColumn(0);
		String[] ySerie = extractColumn(Arrays.asList(data.get(0)).indexOf(y));
		try {
			for (int i = 1; i < xSerie.length; i++) {
				timeSeries.add(parseAsDate(xSerie[i]), Double.parseDouble(ySerie[i]));
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
}
