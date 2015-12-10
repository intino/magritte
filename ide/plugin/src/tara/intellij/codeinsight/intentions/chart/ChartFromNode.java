package tara.intellij.codeinsight.intentions.chart;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
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
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import tara.intellij.lang.psi.MetaIdentifier;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.lang.model.Node;
import tara.lang.model.Parameter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import static org.jfree.chart.labels.StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT;
import static tara.intellij.codeinsight.intentions.chart.DatasetCreator.createDataset;

public class ChartFromNode extends PsiElementBaseIntentionAction implements IntentionAction {
	private static final KeyStroke escapeStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
	private static final String dispatchWindowClosingActionMapKey = "com.spodding.tackline.dispatch:WINDOW_CLOSING";

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		final Node node = TaraPsiImplUtil.getContainerNodeOf(element);
		return isMetaIdentifier(element) && node != null && node.parameters().size() > 1;
	}

	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		final Node node = TaraPsiImplUtil.getContainerNodeOf(element);
		if (node == null) return;
		final List<Node> nodes = node.siblings().stream().filter(n -> n.type().equals(node.type())).collect(Collectors.toList());
		nodes.add(node);
		SwingUtilities.invokeLater(() -> setupUI(node.simpleType(), nodes, editor));
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

	private void setupUI(String nodeType, List<Node> nodes, Editor editor) {
		JFrame frame = new JFrame("Charts");
		frame.setSize(750, 520);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		installEscapeCloseOperation(frame);
		frame.setLocationRelativeTo(editor.getComponent());
		createChartView(nodeType, nodes, frame);
		frame.setVisible(true);
	}

	private void createChartView(String nodeType, List<Node> nodes, JFrame frame) {
		XYDataset dataset = createDataset(nodes, nodes.get(0).parameters().get(0).name(), nodes.get(0).parameters().get(1).name());
		ChartPanel chartPanel = new ChartPanel(ChartFactory.createXYLineChart(nodeType, "x", "y", dataset, PlotOrientation.VERTICAL, true, true, false));
		chartPanel.setMouseZoomable(true, false);
		JToolBar xbar = new JToolBar("");
		setupParameters(nodes, chartPanel, xbar);
		frame.getContentPane().add(chartPanel);
		frame.getContentPane().add(xbar);
	}

	private void setupParameters(List<Node> nodes, ChartPanel chartPanel, JToolBar bar) {
		bar.setBorder(BorderFactory.createLineBorder(JBColor.BLACK));
		bar.setBorderPainted(true);
		bar.setMaximumSize(new Dimension(chartPanel.getWidth(), 20));
		final ComboBox xBox = createBoxForParameters(nodes.get(0));
		final ComboBox yBox = createBoxForParameters(nodes.get(0));
		xBox.addItemListener(new ChangeListener(chartPanel, nodes, xBox, yBox));
		yBox.addItemListener(new ChangeListener(chartPanel, nodes, xBox, yBox));
		bar.add(new Label("x -> "));
		bar.add(xBox);
		bar.addSeparator();
		bar.add(new Label("y -> "));
		bar.add(yBox);
	}

	private ComboBox createBoxForParameters(Node node) {
		List<String> values = node.parameters().stream().map(Parameter::name).collect(Collectors.toList());
		return new ComboBox(values.toArray(new String[values.size()]));
	}

	private boolean isMetaIdentifier(@NotNull PsiElement element) {
		PsiElement anElement = element;
		while (anElement != null && !(anElement instanceof MetaIdentifier)) anElement = anElement.getParent();
		return anElement != null;
	}

	public static void installEscapeCloseOperation(final JFrame frame) {
		Action dispatchClosing = new AbstractAction() {
			public void actionPerformed(ActionEvent event) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		};
		JRootPane root = frame.getRootPane();
		root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeStroke, dispatchWindowClosingActionMapKey);
		root.getActionMap().put(dispatchWindowClosingActionMapKey, dispatchClosing);
	}


	private static class ChangeListener implements java.awt.event.ItemListener {
		private final ChartPanel panel;
		private final List<Node> nodes;
		private final ComboBox xParameter;
		private final ComboBox yParameter;

		public ChangeListener(ChartPanel panel, List<Node> nodes, ComboBox xParameter, ComboBox yParameter) {
			this.panel = panel;
			this.nodes = nodes;
			this.xParameter = xParameter;
			this.yParameter = yParameter;
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			final String x = getSelectedParameter(xParameter);
			final String y = getSelectedParameter(yParameter);
			panel.setChart(createChart(x, y));
		}

		@NotNull
		private JFreeChart createChart(String x, String y) {
			final JFreeChart chart;
			final XYDataset dataset = createDataset(nodes, x, y);
			if (dataset instanceof TimeSeriesCollection) {
				chart = ChartFactory.createTimeSeriesChart(nodes.get(0).simpleType(), x, y, dataset, false, true, false);
				final XYItemRenderer renderer = chart.getXYPlot().getRenderer();
				final StandardXYToolTipGenerator g = new StandardXYToolTipGenerator(DEFAULT_TOOL_TIP_FORMAT, new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00"));
				renderer.setBaseToolTipGenerator(g);
			} else
				chart = ChartFactory.createXYLineChart(nodes.get(0).simpleType(), x, y, dataset, PlotOrientation.VERTICAL, true, true, false);
			return chart;
		}

		private String getSelectedParameter(ComboBox comboBox) {
			return comboBox.getSelectedItem().toString();
		}
	}
}
