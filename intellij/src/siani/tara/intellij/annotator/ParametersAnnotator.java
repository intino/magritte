package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.annotator.semanticAnalizers.ParametersAnalyzer;
import siani.tara.intellij.annotator.semanticAnalizers.ParametersExistenceAnalyzer;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.Parameters;

public class ParametersAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (Concept.class.isInstance(element) && ((Concept) element).getParameters().length == 0)
			analyzeAndAnnotate(new ParametersExistenceAnalyzer((Concept) element));
		if (!Parameters.class.isInstance(element)) return;
		analyzeAndAnnotate(new ParametersAnalyzer((Parameters) element));
	}
}
