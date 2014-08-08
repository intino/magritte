package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.TaraBundle;
import siani.tara.intellij.annotator.fix.RemoveConceptFix;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;

import java.util.ArrayList;
import java.util.List;

public class ConceptAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (!(element instanceof Concept)) return;
		Concept concept = (Concept) element;
		if (isRootCase(concept)) {
			annotateAndFix(element, new RemoveConceptFix(concept), TaraBundle.message("concept.position.key.error.message"));
			return;
		}
		isDuplicated(concept);
	}

	private boolean isRootCase(Concept element) {
		return (element.isCase() && TaraPsiImplUtil.getContextOf(element) == null);
	}

	private void isDuplicated(Concept concept) {
		if (concept.getIdentifierNode() != null && findDuplicates(concept) != 1)
			annotateAndFix(concept.getIdentifierNode(), new RemoveConceptFix(concept), TaraBundle.message("duplicate.concept.key.error.message"));
	}

	public int findDuplicates(Concept concept) {
		Concept parent = TaraPsiImplUtil.getContextOf(concept);
		if (concept.getName() == null) return 1;
		if (parent != null)
			return checkChildDuplicates(concept, parent);
		return searchConceptInFile(concept).size();
	}

	private int checkChildDuplicates(Concept concept, Concept parent) {
		int duplicates = 0;
		for (Concept taraConcept : TaraPsiImplUtil.getChildrenOf(parent))
			if (taraConcept.getName() != null && taraConcept.getName().equals(concept.getName()))
				duplicates++;
		return duplicates;
	}

	private List<Concept> searchConceptInFile(Concept concept) {
		List<Concept> list = new ArrayList<>();
		for (Concept aConcept : concept.getFile().getConcepts())
			if (concept.getName().equals(aConcept.getName())) list.add(aConcept);
		return list;
	}
}
