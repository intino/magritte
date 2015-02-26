package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.annotator.semanticanalizer.FacetTargetAnalyzer;
import siani.tara.intellij.lang.psi.TaraFacetTarget;

public class FacetTargetAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (!TaraFacetTarget.class.isInstance(element)) return;
		analyzeAndAnnotate(new FacetTargetAnalyzer((TaraFacetTarget) element));
	}
}
