package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.annotator.semanticAnalizers.ParametersAnalyzer;
import siani.tara.intellij.lang.psi.Parameters;

public class ParametersAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (!Parameters.class.isInstance(element)) return;
		this.holder = holder;
		analyzeAndAnnotate(new ParametersAnalyzer((Parameters) element));
	}
}
