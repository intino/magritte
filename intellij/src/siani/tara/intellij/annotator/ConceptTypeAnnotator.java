package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.annotator.semanticanalizer.ConceptTypeAnalyzer;
import siani.tara.intellij.lang.psi.MetaIdentifier;

public class ConceptTypeAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (!MetaIdentifier.class.isInstance(element)) return;
		this.holder = holder;
		analyzeAndAnnotate(new ConceptTypeAnalyzer((MetaIdentifier) element));
	}
}



