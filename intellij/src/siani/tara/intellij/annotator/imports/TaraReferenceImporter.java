package siani.tara.intellij.annotator.imports;

import com.intellij.psi.PsiElement;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraFile;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaraReferenceImporter {
	@NotNull
	public static List<ImportQuickFix> proposeImportFix(final PsiElement node) {
		List<Concept> concepts = TaraUtil.findRootConcept(node, node.getText());
		ArrayList<ImportQuickFix> quickFixes = new ArrayList<>();
		if (concepts.isEmpty()) return Collections.EMPTY_LIST;
		for (Concept concept : concepts) quickFixes.add(new ImportQuickFix((TaraFile) node.getContainingFile(),concept));
		return quickFixes;
	}
}