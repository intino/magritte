package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.annotator.semanticAnalizers.BoxAnalyzer;
import siani.tara.intellij.lang.psi.TaraHeaderReference;

public class BoxReferenceAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof TaraHeaderReference) analyzeAndAnnotate(new BoxAnalyzer((TaraHeaderReference) element));
	}
}
