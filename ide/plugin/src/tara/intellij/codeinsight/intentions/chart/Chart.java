package tara.intellij.codeinsight.intentions.chart;

import org.jetbrains.annotations.NotNull;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

import static java.awt.event.KeyEvent.VK_ESCAPE;
import static org.jfree.chart.labels.StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT;

public class Chart extends JDialog {
	private static final KeyStroke escapeStroke = KeyStroke.getKeyStroke(VK_ESCAPE, 0);
	private static final String dispatchWindowClosingActionMapKey = "com.spodding.tackline.dispatch:WINDOW_CLOSING";
	private XYDataset dataset;
	private ChartPanel chartPanel;
	private JPanel contentPane;
	private JButton buttonOK;
	private JComboBox x;
	private JComboBox y;
	private JPanel root;
	private JComboBox drill;
	private JPanel drillPane;

	public Chart(String title, DatasetCreator creator, JComponent parent) {
		init(title, parent);
		this.dataset = creator.createDataset(0, 1, -1);
		this.chartPanel = new ChartPanel(newChart(dataset), true);
		this.chartPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		setupParameters(creator);
		root.add(chartPanel);
	}

	@NotNull
	private static JFreeChart newChart(XYDataset dataset) {
		return ChartFactory.createXYLineChart("", "", "", dataset, PlotOrientation.VERTICAL, true, true, false);
	}

	private void setupParameters(DatasetCreator creator) {
		drill.addItem("");
		creator.header().stream().forEach(v -> {
			x.addItem(v);
			y.addItem(v);
		});
		for (int i = 2; i < creator.header().size(); i++) drill.addItem(creator.header().get(i));
		x.setSelectedItem(creator.header().get(0));
		y.setSelectedItem(creator.header().get(1));
		x.addItemListener(new AxisChangeListener(chartPanel, creator));
		y.addItemListener(new AxisChangeListener(chartPanel, creator));
		drill.addItemListener(new DrillChangeListener(chartPanel, creator));
	}

	private void init(String title, JComponent parent) {
		this.setMinimumSize(new Dimension(768, 520));
		this.setSize(768, 520);
		this.setTitle(title);
		this.setContentPane(contentPane);
		this.setLocationRelativeTo(parent);
		this.setModal(true);
		closeOnScape();
		getRootPane().setDefaultButton(buttonOK);
		buttonOK.addActionListener(e -> onOK());
	}

	private void onOK() {
		dispose();
	}


	public void closeOnScape() {
		Action dispatchClosing = new AbstractAction() {
			public void actionPerformed(ActionEvent event) {
				Chart.this.dispatchEvent(new WindowEvent(Chart.this, WindowEvent.WINDOW_CLOSING));
			}
		};
		JRootPane root = Chart.this.getRootPane();
		root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeStroke, dispatchWindowClosingActionMapKey);
		root.getActionMap().put(dispatchWindowClosingActionMapKey, dispatchClosing);
	}

	private class AxisChangeListener implements ItemListener {
		private final ChartPanel panel;
		private final DatasetCreator creator;

		public AxisChangeListener(ChartPanel panel, DatasetCreator creator) {
			this.panel = panel;
			this.creator = creator;
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			updateDrill();
			panel.setChart(createChart(x.getSelectedIndex(), y.getSelectedIndex()));
		}

		private void updateDrill() {
			drill.getItemListeners();
			drill.removeAllItems();
			final java.util.List<String> toDrill = creator.header().stream().filter(v -> !x.getSelectedItem().toString().equals(v) && !y.getSelectedItem().toString().equals(v)).collect(Collectors.toList());
			if (!toDrill.isEmpty()) {
				drillPane.setVisible(true);
				drill.addItem("");
				toDrill.forEach(v -> drill.addItem(v));
				drill.addItemListener(new DrillChangeListener(panel, creator));
			} else drillPane.setVisible(false);
		}

		@NotNull
		private JFreeChart createChart(int x, int y) {
			final JFreeChart chart;
			final XYDataset dataset = creator.createDataset(x, y, -1);
			if (dataset instanceof TimeSeriesCollection) {
				chart = ChartFactory.createTimeSeriesChart("", "", "", dataset, false, true, false);
				final XYItemRenderer renderer = chart.getXYPlot().getRenderer();
				final StandardXYToolTipGenerator g = new StandardXYToolTipGenerator(DEFAULT_TOOL_TIP_FORMAT, new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00"));
				renderer.setBaseToolTipGenerator(g);
			} else chart = newChart(dataset);
			return chart;
		}
	}

	private class DrillChangeListener implements ItemListener {
		private final ChartPanel panel;
		private final DatasetCreator creator;

		private DrillChangeListener(ChartPanel panel, DatasetCreator creator) {
			this.panel = panel;
			this.creator = creator;
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			panel.setChart(createChart(x.getSelectedIndex(), y.getSelectedIndex()));
		}

		@NotNull
		private JFreeChart createChart(int x, int y) {
			final JFreeChart chart;
			final XYDataset dataset = creator.createDataset(x, y, creator.header().indexOf(drill.getSelectedItem()));
			if (dataset instanceof TimeSeriesCollection) {
				chart = ChartFactory.createTimeSeriesChart("", "", "", dataset, false, true, false);
				final XYItemRenderer renderer = chart.getXYPlot().getRenderer();
				final StandardXYToolTipGenerator g = new StandardXYToolTipGenerator(DEFAULT_TOOL_TIP_FORMAT, new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00"));
				renderer.setBaseToolTipGenerator(g);
			} else chart = newChart(dataset);
			return chart;
		}
	}
}
