package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.annotator.semanticanalizer.ParametersAnalyzer;
import siani.tara.intellij.annotator.semanticanalizer.ParametersExistenceAnalyzer;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.FacetApply;
import siani.tara.intellij.lang.psi.Parameters;

public class ParametersAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (isConceptWithoutParams(element) || isFacetWithoutParams(element))
			analyzeAndAnnotate(new ParametersExistenceAnalyzer(element));

		if (Parameters.class.isInstance(element))
			analyzeAndAnnotate(new ParametersAnalyzer((Parameters) element));
	}

	private boolean isConceptWithoutParams(PsiElement element) {
		return Concept.class.isInstance(element) && ((Concept) element).getParameterList().length == 0;
	}

	private boolean isFacetWithoutParams(PsiElement element) {
		return FacetApply.class.isInstance(element) &&
			(((FacetApply) element).getParameters() == null || ((FacetApply) element).getParameters().getParameters().length == 0);
	}
}
