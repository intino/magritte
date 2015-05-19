package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.annotator.semanticanalizer.ReferenceAnalyzer;
import siani.tara.intellij.lang.psi.IdentifierReference;

public class ReferenceAnnotator extends TaraAnnotator {


	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (!IdentifierReference.class.isInstance(element)) return;
		this.holder = holder;
		IdentifierReference reference = (IdentifierReference) element;
		if (reference.getIdentifierList().get(0).getReference() == null) return;
		analyzeAndAnnotate(new ReferenceAnalyzer(reference));
	}
}
