package tara.intellij.codeinsight.intentions.chart;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
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
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import tara.intellij.lang.psi.MetaIdentifier;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.language.model.Node;
import tara.language.model.Parameter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
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
		SwingUtilities.invokeLater(() -> {
			setupUI(node.simpleType(), nodes, editor);
		});

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
		frame.setSize(855, 520);
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
		JToolBar xbar = new JToolBar("x");
		JToolBar ybar = new JToolBar("y");
		setupButtons(nodes, chartPanel, xbar, ybar);
		frame.getContentPane().add(chartPanel);
		frame.getContentPane().add(xbar);
		frame.getContentPane().add(ybar);
	}

	private void setupButtons(List<Node> nodes, ChartPanel chartPanel, JToolBar xbar, JToolBar ybar) {
		xbar.setBorderPainted(true);
		xbar.add(new Label("x -> "));
		ybar.add(new Label("y -> "));
		ybar.setBorder(BorderFactory.createLineBorder(JBColor.BLACK));
		xbar.setBorder(BorderFactory.createLineBorder(JBColor.BLACK));
		ybar.setBorderPainted(true);
		xbar.setFloatable(false);
		ybar.setFloatable(false);
		createButtons(nodes, chartPanel, xbar, ybar);
		xbar.setMaximumSize(new Dimension(chartPanel.getWidth(), 20));
		ybar.setMaximumSize(new Dimension(chartPanel.getWidth(), 20));
	}

	private void createButtons(List<Node> nodes, ChartPanel panel, JToolBar xbar, JToolBar ybar) {
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

	private boolean isMetaIdentifier(@NotNull PsiElement element) {
		PsiElement anElement = element;
		while (anElement != null && !(anElement instanceof MetaIdentifier)) anElement = anElement.getParent();
		return anElement != null;
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
