package tara.intellij.codeinsight.intentions;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import tara.intellij.lang.psi.MetaIdentifier;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.language.model.Node;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

public class ChartFromNode extends PsiElementBaseIntentionAction implements IntentionAction {

	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		final Node node = TaraPsiImplUtil.getContainerNodeOf(element);
		if (node == null) return;
		final List<Node> nodes = node.siblings().stream().filter(n -> n.type().equals(node.type())).collect(Collectors.toList());
		nodes.add(node);
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Charts");

			frame.setSize(600, 400);
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			frame.setVisible(true);

			XYDataset ds = createDataset();
			JFreeChart chart = ChartFactory.createXYLineChart("Test Chart",
				"x", "y", ds, PlotOrientation.VERTICAL, true, true,
				false);

			ChartPanel cp = new ChartPanel(chart);

			frame.getContentPane().add(cp);
		});

	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		final Node node = TaraPsiImplUtil.getContainerNodeOf(element);
		return element instanceof MetaIdentifier && node != null && !node.parameters().isEmpty();
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


	private XYDataset createDataset() {
		DefaultXYDataset ds = new DefaultXYDataset();
		double[][] data = {{0.1, 0.2, 0.3}, {1, 2, 3}};
		ds.addSeries("series1", data);
		return ds;
	}
}
