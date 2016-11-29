package tara.intellij.codeinsight.intentions.chart;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.FileUtilRt;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.TaraTypes;
import tara.intellij.lang.psi.TaraWithTable;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.lang.model.Node;

import javax.swing.*;

@SuppressWarnings("UseJBColor")
public class ChartFromTable extends PsiElementBaseIntentionAction implements IntentionAction {

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		return element.getNode().getElementType().equals(TaraTypes.IDENTIFIER_KEY) && TaraPsiImplUtil.getContainerByType(element, TaraWithTable.class) != null;
	}

	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		final PsiElement tableFile = ReferenceManager.resolveTable(element);
		final Node node = TaraPsiImplUtil.getContainerNodeOf(element);
		if (node == null || tableFile == null || !(tableFile instanceof PsiFile)) return;
		SwingUtilities.invokeLater(() -> show((PsiFile) tableFile, editor));
	}

	private void show(PsiFile tableFile, Editor editor) {
		Chart chart = new Chart(FileUtilRt.getNameWithoutExtension(tableFile.getName()), new DatasetCreator(tableFile), editor.getComponent());
		chart.pack();
		chart.setVisible(true);
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

}
