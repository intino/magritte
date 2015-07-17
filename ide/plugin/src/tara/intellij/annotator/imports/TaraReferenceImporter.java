package tara.intellij.annotator.imports;

import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.Identifier;
import tara.intellij.lang.psi.IdentifierReference;
import tara.intellij.lang.psi.Node;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.impl.TaraUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaraReferenceImporter {

	private TaraReferenceImporter() {
	}

	@NotNull
	public static List<ImportQuickFix> proposeImportFix(final IdentifierReference node) {
		Identifier element = node.getIdentifierList().get(0);
		List<Node> nodes = TaraUtil.findRootNode(element, element.getText());
		ArrayList<ImportQuickFix> quickFixes = new ArrayList<>();
		if (nodes.isEmpty()) return Collections.EMPTY_LIST;
		for (Node concept : nodes) quickFixes.add(new ImportQuickFix((TaraModel) node.getContainingFile(), concept));
		return quickFixes;
	}
}