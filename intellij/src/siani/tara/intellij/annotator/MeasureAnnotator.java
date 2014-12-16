package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.annotator.semanticAnalizers.MeasureAnalyzer;
import siani.tara.intellij.lang.psi.TaraAttributeType;

public class MeasureAnnotator extends TaraAnnotator {
	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (!TaraAttributeType.class.isInstance(element)) return;
		this.holder = holder;
		analyzeAndAnnotate(new MeasureAnalyzer((TaraAttributeType) element));
	}
}
