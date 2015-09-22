package tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tara.intellij.annotator.semanticanalizer.HeaderReferenceAnalyzer;
import tara.intellij.annotator.semanticanalizer.ReferenceAnalyzer;
import tara.intellij.lang.psi.Contract;
import tara.intellij.lang.psi.HeaderReference;
import tara.intellij.lang.psi.IdentifierReference;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.language.model.Primitives;
import tara.language.model.Variable;

import static tara.language.model.Primitives.NATIVE;
import static tara.language.model.Primitives.WORD;

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
		if (reference.getIdentifierList().isEmpty() || reference.getIdentifierList().get(0).getReference() == null || isMetric(reference))
			return;
		analyzeAndAnnotate(new ReferenceAnalyzer(reference));
	}

	private boolean isMetric(IdentifierReference reference) {
		final Variable variable = TaraPsiImplUtil.getContainerByType(reference, Variable.class);
		return reference.getParent() instanceof Contract && variable != null && !WORD.equals(variable.type()) && !NATIVE.equals(variable.type())
			&& !Primitives.MEASURE.equals(variable.type());
	}
}
