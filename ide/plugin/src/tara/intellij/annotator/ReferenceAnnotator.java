package tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tara.intellij.annotator.semanticanalizer.HeaderReferenceAnalyzer;
import tara.intellij.annotator.semanticanalizer.ReferenceAnalyzer;
import tara.intellij.lang.psi.HeaderReference;
import tara.intellij.lang.psi.IdentifierReference;
import tara.intellij.lang.psi.Rule;
import tara.intellij.lang.psi.TaraMethodReference;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.lang.model.Variable;

import static tara.lang.model.Primitive.FUNCTION;
import static tara.lang.model.Primitive.WORD;

public class ReferenceAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (!IdentifierReference.class.isInstance(element) && !HeaderReference.class.isInstance(element)) return;
		this.holder = holder;
		if (IdentifierReference.class.isInstance(element)) asIdentifierReference((IdentifierReference) element);
		else if (IdentifierReference.class.isInstance(element) && element.getContext() instanceof TaraMethodReference)
			asMethodReference((IdentifierReference) element);
		else if (HeaderReference.class.isInstance(element)) asHeaderReference((HeaderReference) element);
	}

	private void asHeaderReference(HeaderReference reference) {
		if (!reference.getIdentifierList().isEmpty()) analyzeAndAnnotate(new HeaderReferenceAnalyzer(reference));
	}

	private void asIdentifierReference(IdentifierReference reference) {
		if (!reference.getIdentifierList().isEmpty() && reference.getIdentifierList().get(0).getReference() != null && !isRule(reference))
			analyzeAndAnnotate(new ReferenceAnalyzer(reference));
	}

	private void asMethodReference(IdentifierReference reference) {
		if (!reference.getIdentifierList().isEmpty()) analyzeAndAnnotate(new ReferenceAnalyzer(reference));
	}

	private boolean isRule(IdentifierReference reference) {
		final Variable variable = TaraPsiImplUtil.getContainerByType(reference, Variable.class);
		return reference.getParent() instanceof Rule && variable != null &&
			!WORD.equals(variable.type()) &&
			!FUNCTION.equals(variable.type());
	}
}
