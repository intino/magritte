package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.annotator.semanticanalizer.HeaderReferenceAnalyzer;
import siani.tara.intellij.annotator.semanticanalizer.ReferenceAnalyzer;
import siani.tara.intellij.lang.psi.HeaderReference;
import siani.tara.intellij.lang.psi.IdentifierReference;

public class ReferenceAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (!IdentifierReference.class.isInstance(element) && !HeaderReference.class.isInstance(element)) return;
		this.holder = holder;
		if (IdentifierReference.class.isInstance(element)) asIdentifierReference((IdentifierReference) element);
		if (HeaderReference.class.isInstance(element)) asHeaderReference((HeaderReference) element);
	}

	private void asHeaderReference(HeaderReference reference) {
		if (reference.getIdentifierList().isEmpty() || reference.getIdentifierList().get(0).getReference() == null)
			return;
		analyzeAndAnnotate(new HeaderReferenceAnalyzer(reference));
	}

	private void asIdentifierReference(IdentifierReference reference) {
		if (reference.getIdentifierList().isEmpty() || reference.getIdentifierList().get(0).getReference() == null)
			return;
		analyzeAndAnnotate(new ReferenceAnalyzer(reference));
	}
}
