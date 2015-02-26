package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.annotator.semanticanalizer.ParameterAnalyzer;
import siani.tara.intellij.lang.psi.Parameter;

public class ParameterAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (!(element instanceof Parameter)) return;
		this.holder = holder;
		Parameter parameter = (Parameter) element;
		analyzeAndAnnotate(new ParameterAnalyzer(parameter));
	}
}
