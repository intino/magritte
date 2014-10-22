package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.TaraBundle;
import siani.tara.intellij.annotator.fix.RemoveConceptFix;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraFacetTarget;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ConceptAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (!Concept.class.isInstance(element)) return;
		this.holder = holder;
		Concept concept = (Concept) element;
		if (isRootSub(concept)) {
			annotateAndFix(element, new RemoveConceptFix(concept), TaraBundle.message("concept.position.key.error.message"));
			return;
		}
		checkIfDuplicated(concept);
		checkIfExtendedFromDifferentType(concept);
	}

	private void checkIfExtendedFromDifferentType(Concept concept) {
		if (concept.getSignature().getParentConcept() == null || concept.getType() == null) return;
		if (!concept.getType().equals(concept.getSignature().getParentConcept().getType()))
			annotateAndFix(concept.getSignature().getParentReference(), new RemoveConceptFix(concept), TaraBundle.message("invalid.extension.concept.key.error.message"));
	}

	private boolean isRootSub(Concept element) {
		return (element.isSub() && TaraPsiImplUtil.getConceptContextOf(element) == null);
	}

	private void checkIfDuplicated(Concept concept) {
		if (concept.getIdentifierNode() != null && findDuplicates(concept) > 1)
			annotateAndFix(concept.getIdentifierNode(), new RemoveConceptFix(concept), TaraBundle.message("duplicate.concept.key.error.message"));
	}

	public int findDuplicates(Concept concept) {
		if (concept.getName() == null) return 1;
		Concept parent = TaraPsiImplUtil.getConceptContextOf(concept);
		if (parent != null)
			return checkChildDuplicates(concept, parent);
		else {
			PsiElement inFacetTarget = TaraPsiImplUtil.getContextOf(concept);
			if (inFacetTarget != null && inFacetTarget instanceof TaraFacetTarget) {
				List<Concept> innerConceptsInBody = TaraPsiImplUtil.getInnerConceptsInBody(((TaraFacetTarget) inFacetTarget).getBody());
				return countDuplicates(innerConceptsInBody, concept.getName());
			}
		}
		return searchConceptInFile(concept).size();
	}

	private int checkChildDuplicates(Concept concept, Concept parent) {
		Collection<Concept> innerConceptsOf = concept.isSub() ? parent.getSubConcepts() : TaraPsiImplUtil.getInnerConceptsOf(parent);
		String name = concept.getName();
		return countDuplicates(innerConceptsOf, name);
	}

	private int countDuplicates(Collection<Concept> innerConceptsOf, String name) {
		int duplicates = 0;
		for (Concept taraConcept : innerConceptsOf)
			if (taraConcept.getName() != null && taraConcept.getName().equals(name))
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
