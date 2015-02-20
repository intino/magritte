package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.annotator.semanticAnalizers.ParametersAnalyzer;
import siani.tara.intellij.annotator.semanticAnalizers.ParametersExistenceAnalyzer;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.FacetApply;
import siani.tara.intellij.lang.psi.Parameters;

public class ParametersAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if ((Concept.class.isInstance(element) && ((Concept) element).getParameterList().length == 0) ||
			(FacetApply.class.isInstance(element) && (((FacetApply) element).getParameters() == null || ((FacetApply) element).getParameters().getParameters().length == 0)))
			analyzeAndAnnotate(new ParametersExistenceAnalyzer(element));
		if (!Parameters.class.isInstance(element)) return;
		analyzeAndAnnotate(new ParametersAnalyzer((Parameters) element));
	}
}
