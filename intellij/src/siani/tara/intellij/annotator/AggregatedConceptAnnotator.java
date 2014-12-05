package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.annotator.semanticAnalizers.AggregatedAnalyzer;
import siani.tara.intellij.lang.psi.Concept;

public class AggregatedConceptAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (Concept.class.isInstance(element)) analyzeAndAnnotate(new AggregatedAnalyzer((Concept) element));
	}
}
