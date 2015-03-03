package siani.tara.intellij.annotator.imports;

import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.Identifier;
import siani.tara.intellij.lang.psi.IdentifierReference;
import siani.tara.intellij.lang.psi.TaraBoxFile;
import siani.tara.intellij.lang.psi.impl.TaraUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaraReferenceImporter {

	private TaraReferenceImporter() {
	}

	@NotNull
	public static List<ImportQuickFix> proposeImportFix(final IdentifierReference node) {
		Identifier element = node.getIdentifierList().get(0);
		List<Concept> concepts = TaraUtil.findRootConcept(element, element.getText());
		ArrayList<ImportQuickFix> quickFixes = new ArrayList<>();
		if (concepts.isEmpty()) return Collections.EMPTY_LIST;
		for (Concept concept : concepts) quickFixes.add(new ImportQuickFix((TaraBoxFile) node.getContainingFile(),concept));
		return quickFixes;
	}
}