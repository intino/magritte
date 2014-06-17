package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import siani.tara.intellij.TaraBundle;
import siani.tara.intellij.annotator.fix.RenameConceptFix;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraFile;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import org.jetbrains.annotations.NotNull;

public class ConceptAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof Concept) {
			isDuplicated((Concept) element);
			if (element.getParent() instanceof TaraFile)
				isNameEqualsFileName((Concept) element);
		}
	}

	private void isNameEqualsFileName(Concept concept) {
		if (concept.getIdentifierNode() != null && !concept.getIdentifierNode().getText().equals(concept.getFile().getPresentableName()))
			annotateAndFix(concept.getIdentifierNode(), new RenameConceptFix(concept), TaraBundle.message("prime.concept.name.different.file.error.message"));
	}

	private void isDuplicated(Concept concept) {
		if (concept.getIdentifierNode() != null && TaraUtil.findDuplicates(concept) != 1)
			annotateAndFix(concept.getIdentifierNode(), new RenameConceptFix(concept), TaraBundle.message("duplicate.concept.key.error.message"));
	}
}
