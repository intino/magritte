package monet.tara.intellij.annotator.imports;

import com.intellij.psi.PsiElement;
import monet.tara.intellij.metamodel.psi.Concept;
import monet.tara.intellij.metamodel.psi.TaraFile;
import monet.tara.intellij.metamodel.psi.impl.TaraUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaraReferenceImporter {
	@NotNull
	public static List<ImportQuickFix> proposeImportFix(final PsiElement node) {
		List<Concept> concepts = TaraUtil.findRootConcept(node.getProject(), node.getText());
		ArrayList<ImportQuickFix> quickFixes = new ArrayList<>();
		if (concepts.isEmpty()) return Collections.EMPTY_LIST;
		for (Concept concept : concepts) quickFixes.add(new ImportQuickFix((TaraFile) node.getContainingFile(),concept));
		return quickFixes;
	}
}