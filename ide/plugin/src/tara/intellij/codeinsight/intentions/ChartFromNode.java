package tara.intellij.codeinsight.intentions;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.ui.JBColor;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import tara.intellij.lang.psi.MetaIdentifier;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.language.model.Node;
import tara.language.model.Parameter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

public class ChartFromNode extends PsiElementBaseIntentionAction implements IntentionAction {
	private static final Logger LOG = Logger.getInstance(ChartFromNode.class.getName());

	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		final Node node = TaraPsiImplUtil.getContainerNodeOf(element);
		if (node == null) return;
		final List<Node> nodes = node.siblings().stream().filter(n -> n.type().equals(node.type())).collect(Collectors.toList());
		nodes.add(node);
		SwingUtilities.invokeLater(() -> {
			setupUI(node.simpleType(), nodes, editor);
		});

	}

	private void setupUI(String nodeType, List<Node> nodes, Editor editor) {
		JFrame frame = new JFrame("Charts");
		frame.setSize(850, 520);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(editor.getComponent());
		XYDataset dataset = createDataset(nodes, nodes.get(0).parameters().get(0).name(), nodes.get(0).parameters().get(1).name());
		ChartPanel chartPanel = new ChartPanel(ChartFactory.createXYLineChart(nodeType, "x", "y", dataset, PlotOrientation.VERTICAL, true, true, false));
		chartPanel.setMouseZoomable(true, false);
		JToolBar xbar = new JToolBar("x");
		JToolBar ybar = new JToolBar("y");
		setupButtons(nodes, chartPanel, xbar, ybar, chartPanel);
		frame.getContentPane().add(chartPanel);
		frame.getContentPane().add(xbar);
		frame.getContentPane().add(ybar);
		frame.setVisible(true);
	}

	private void setupButtons(List<Node> nodes, ChartPanel chartPanel, JToolBar xbar, JToolBar ybar, ChartPanel panel) {
		xbar.setBorderPainted(true);
		xbar.add(new Label("x -> "));
		ybar.add(new Label("y -> "));
		ybar.setBorder(BorderFactory.createLineBorder(JBColor.BLACK));
		xbar.setBorder(BorderFactory.createLineBorder(JBColor.BLACK));
		ybar.setBorderPainted(true);
		xbar.setFloatable(false);
		ybar.setFloatable(false);
		ButtonGroup xGroup = new ButtonGroup();
		ButtonGroup yGroup = new ButtonGroup();
		boolean xSelected = false;
		boolean ySelected = false;
		for (Parameter parameter : nodes.get(0).parameters()) {
			final JRadioButton xButton = new JRadioButton(parameter.name());
			if (!xSelected) xButton.setSelected(xSelected = true);
			xButton.addChangeListener(new RadioChangeListener(panel, nodes, xGroup, yGroup));
			xGroup.add(xButton);
			xbar.add(xButton);
			final JRadioButton yButton = new JRadioButton(parameter.name());
			if (!ySelected) xButton.setSelected(ySelected = true);
			yButton.addChangeListener(new RadioChangeListener(panel, nodes, xGroup, yGroup));
			yGroup.add(yButton);
			ybar.add(yButton);
		}
		xbar.setMaximumSize(new Dimension(chartPanel.getWidth(), 20));
		ybar.setMaximumSize(new Dimension(chartPanel.getWidth(), 20));
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		final Node node = TaraPsiImplUtil.getContainerNodeOf(element);
		return isMetaIdentifier(element) && node != null && node.parameters().size() > 1;
	}

	private boolean isMetaIdentifier(@NotNull PsiElement element) {
		PsiElement anElement = element;
		while (anElement != null && !(anElement instanceof MetaIdentifier)) anElement = anElement.getParent();
		return anElement != null;
	}

	@Nls
	@NotNull
	@Override
	public String getFamilyName() {
		return getText();
	}

	@NotNull
	@Override
	public String getText() {
		return "Generate chart for this values";
	}


	private static XYDataset createDataset(List<Node> nodes, String x, String y) {
		List<SimpleEntry<Object, Double>> data = collectData(nodes, x, y);
		if (!data.isEmpty()) return fillDataSet(x, y, data);
		else {
			DefaultXYDataset ds = new DefaultXYDataset();
			ds.addSeries("No representable values", new double[][]{{0}, {0}});
			return ds;
		}
	}

	@NotNull
	private static List<SimpleEntry<Object, Double>> collectData(List<Node> nodes, String x, String y) {
		List<SimpleEntry<Object, Double>> data = new ArrayList<>();
		for (Node node : nodes) {
			final Parameter xParameter = findParameter(node, x);
			final Parameter yParameter = findParameter(node, y);
			if (isCompatible(xParameter) && isCompatible(yParameter))
				data.add(new SimpleEntry<>(asCompatibleValue(xParameter), (Double) yParameter.values().get(0)));
		}
		return data;
	}

	@NotNull
	private static XYDataset fillDataSet(String x, String y, List<SimpleEntry<Object, Double>> data) {
		final boolean timeSeries = isTimeSerie(data);
		if (timeSeries) {
			TimeSeriesCollection ds = new TimeSeriesCollection();
			ds.addSeries(toTimeSerie(data));
			return ds;
		} else {
			DefaultXYDataset ds = new DefaultXYDataset();
			ds.addSeries(x + " / " + y, toDoubleMatrix(data));
			return ds;
		}
	}

	private static boolean isTimeSerie(List<SimpleEntry<Object, Double>> data) {
		return !(data.get(0).getKey() instanceof Double);
	}

	private static boolean isCompatible(Parameter parameter) {
		return parameter != null && parameter.values().size() == 1 && (parameter.values().get(0) instanceof Double || parseAsDate(parameter) != null);
	}

	private static Object asCompatibleValue(Parameter x) {
		if (x.values().get(0) instanceof Double)
			return x.values().get(0);
		else {
			LocalDateTime dateTime = parseAsDate(x);
			if (dateTime == null) return null;
			return new Second(dateTime.getSecond(), dateTime.getMinute(), dateTime.getHour(), dateTime.getDayOfMonth(), dateTime.getMonthValue(), dateTime.getYear());
		}
	}

	private static LocalDateTime parseAsDate(Parameter x) {
		try {
			final Object o = x.values().get(0);
			if (o == null) return null;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			return LocalDateTime.parse(o.toString().replace("\"", ""), formatter);
		} catch (DateTimeParseException e) {
			LOG.info(e.getMessage(), e);
			return null;
		}
	}

	private static double[][] toDoubleMatrix(List<SimpleEntry<Object, Double>> data) {
		double[][] doubles = new double[2][data.size()];
		for (int i = 0; i < data.size(); i++) {
			doubles[0][i] = (double) data.get(i).getKey();
			doubles[1][i] = data.get(i).getValue();
		}
		return doubles;
	}

	private static TimeSeries toTimeSerie(List<SimpleEntry<Object, Double>> data) {
		final TimeSeries timeSeries = new TimeSeries("");
		try {
			for (SimpleEntry<Object, Double> entry : data)
				timeSeries.add((RegularTimePeriod) entry.getKey(), entry.getValue());
		} catch (SeriesException e) {
			LOG.error(e.getMessage(), e);
		}
		return timeSeries;
	}

	private static Parameter findParameter(Node node, String name) {
		for (Parameter parameter : node.parameters())
			if (parameter.name().equals(name)) return parameter;
		return null;
	}

	private static class RadioChangeListener implements javax.swing.event.ChangeListener {
		private final ChartPanel panel;
		private final List<Node> nodes;
		private final ButtonGroup xParameter;
		private final ButtonGroup yParameter;

		public RadioChangeListener(ChartPanel panel, List<Node> nodes, ButtonGroup xParameter, ButtonGroup yParameter) {
			this.panel = panel;
			this.nodes = nodes;
			this.xParameter = xParameter;
			this.yParameter = yParameter;
		}

		@Override
		public void stateChanged(ChangeEvent e) {
			if (!((JRadioButton) e.getSource()).isSelected()) return;
			final String x = getSelectedParameter(xParameter);
			final String y = getSelectedParameter(yParameter);
			panel.setChart(getChart(x, y));
		}

		@NotNull
		private JFreeChart getChart(String x, String y) {
			final JFreeChart chart;
			final XYDataset dataset = createDataset(nodes, x, y);
			if (dataset instanceof TimeSeriesCollection) {
				chart = ChartFactory.createTimeSeriesChart(nodes.get(0).simpleType(), x, y, dataset, true, true, false);
				final XYItemRenderer renderer = chart.getXYPlot().getRenderer();
				final StandardXYToolTipGenerator g = new StandardXYToolTipGenerator(StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT, new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00")
				);
				renderer.setToolTipGenerator(g);
			} else
				chart = ChartFactory.createXYLineChart(nodes.get(0).simpleType(), x, y, dataset, PlotOrientation.VERTICAL, true, true, false);
			return chart;
		}

		private String getSelectedParameter(ButtonGroup group) {
			final Enumeration<AbstractButton> elements = group.getElements();
			while (elements.hasMoreElements()) {
				final AbstractButton button = elements.nextElement();
				if (button.isSelected()) return button.getText();
			}
			return "";
		}
	}
}
