package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.annotator.semanticanalizer.ReferenceAnalyzer;
import siani.tara.intellij.lang.psi.IdentifierReference;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.TaraFacetApply;

public class ReferenceAnnotator extends TaraAnnotator {


	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (!IdentifierReference.class.isInstance(element)) return;
		this.holder = holder;
		IdentifierReference identifier = (IdentifierReference) element;
		analyzeAndAnnotate(new ReferenceAnalyzer(identifier));
	}

	private boolean isInFacetApply(PsiElement element) {
		PsiElement aElement = element;
		TaraFacetApply result;
		while ((aElement.getParent() != null) && !(aElement.getParent() instanceof Node) && !(aElement.getParent() instanceof TaraFacetApply))
			aElement = aElement.getParent();
		result = (aElement.getParent() instanceof TaraFacetApply) ? (TaraFacetApply) aElement.getParent() : null;
		return result != null;
	}
}
