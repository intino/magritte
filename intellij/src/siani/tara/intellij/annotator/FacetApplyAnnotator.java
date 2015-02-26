package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.annotator.semanticanalizer.FacetApplyAnalyzer;
import siani.tara.intellij.lang.psi.TaraFacetApply;

public class FacetApplyAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (!(element instanceof TaraFacetApply)) return;
		this.holder = holder;
		analyzeAndAnnotate(new FacetApplyAnalyzer((TaraFacetApply) element));
	}
}
