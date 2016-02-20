package tara.intellij.codeinsight.intentions.chart;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
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
import tara.intellij.lang.psi.TaraTypes;
import tara.intellij.lang.psi.TaraWithTable;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.lang.model.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import static org.jfree.chart.labels.StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT;

public class ChartFromTable extends PsiElementBaseIntentionAction implements IntentionAction {
	private static final KeyStroke escapeStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
	private static final String dispatchWindowClosingActionMapKey = "com.spodding.tackline.dispatch:WINDOW_CLOSING";

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		return element.getNode().getElementType().equals(TaraTypes.IDENTIFIER_KEY) && TaraPsiImplUtil.getContainerByType(element, TaraWithTable.class) != null;
	}

	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		final PsiElement tableFile = ReferenceManager.resolveTable(element);
		final Node node = TaraPsiImplUtil.getContainerNodeOf(element);
		if (node == null || tableFile == null || !(tableFile instanceof PsiFile)) return;
		SwingUtilities.invokeLater(() -> setupUI(node.simpleType(), (PsiFile) tableFile, editor));
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

	private void setupUI(String nodeType, PsiFile tableFile, Editor editor) {
		JFrame frame = new JFrame("Charts");
		frame.setSize(750, 520);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		installEscapeCloseOperation(frame);
		frame.setLocationRelativeTo(editor.getComponent());
		createChartView(nodeType, tableFile, frame);
		frame.setVisible(true);
	}

	private void createChartView(String nodeType, PsiFile tableFile, JFrame frame) {
		DatasetCreator creator = new DatasetCreator(tableFile);
		XYDataset dataset = creator.createDataset(creator.header()[1]);
		ChartPanel chartPanel = new ChartPanel(ChartFactory.createXYLineChart(nodeType, "x", "y", dataset, PlotOrientation.VERTICAL, true, true, false));
		chartPanel.setMouseZoomable(true, false);
		JToolBar xbar = new JToolBar("");
		setupParameters(creator, chartPanel, xbar);
		frame.getContentPane().add(chartPanel);
		frame.getContentPane().add(xbar);
	}

	private void setupParameters(DatasetCreator creator, ChartPanel chartPanel, JToolBar bar) {
		bar.setBorder(BorderFactory.createLineBorder(JBColor.BLACK));
		bar.setBorderPainted(true);
		bar.setMaximumSize(new Dimension(chartPanel.getWidth(), 20));
		final ComboBox xBox = new ComboBox(creator.header());
		final ComboBox yBox = new ComboBox(creator.header());
		xBox.addItemListener(new ChangeListener(chartPanel, creator, xBox, yBox));
		yBox.addItemListener(new ChangeListener(chartPanel, creator, xBox, yBox));
		bar.add(new Label("x -> "));
		bar.add(xBox);
		bar.addSeparator();
		bar.add(new Label("y -> "));
		bar.add(yBox);
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
		private final DatasetCreator creator;
		private final String[] header;
		private final ComboBox xParameter;
		private final ComboBox yParameter;

		public ChangeListener(ChartPanel panel, DatasetCreator creator, ComboBox xParameter, ComboBox yParameter) {
			this.panel = panel;
			this.creator = creator;
			this.header = creator.header();
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
			final XYDataset dataset = creator.createDataset(y);
			if (dataset instanceof TimeSeriesCollection) {
				chart = ChartFactory.createTimeSeriesChart("type", x, y, dataset, false, true, false);
				final XYItemRenderer renderer = chart.getXYPlot().getRenderer();
				final StandardXYToolTipGenerator g = new StandardXYToolTipGenerator(DEFAULT_TOOL_TIP_FORMAT, new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00"));
				renderer.setBaseToolTipGenerator(g);
			} else
				chart = ChartFactory.createXYLineChart("type", x, y, dataset, PlotOrientation.VERTICAL, true, true, false);
			return chart;
		}

		private String getSelectedParameter(ComboBox comboBox) {
			return comboBox.getSelectedItem().toString();
		}
	}
}
