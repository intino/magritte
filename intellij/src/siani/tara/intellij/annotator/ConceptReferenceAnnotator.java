package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.annotator.semanticAnalizers.ConceptReferenceAnalyzer;
import siani.tara.intellij.lang.psi.TaraConceptReference;
import siani.tara.intellij.lang.psi.TaraIdentifierReference;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;

import java.awt.*;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class ConceptReferenceAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (!TaraConceptReference.class.isInstance(element)) return;
		ConceptReferenceAnalyzer checker = new ConceptReferenceAnalyzer((TaraConceptReference) element);
		analyzeAndAnnotate(checker);
		if (checker.results().isEmpty())
			addReferenceHighlight(holder, ((TaraConceptReference) element).getIdentifierReference());
	}

	@SuppressWarnings("deprecation")
	private void addReferenceHighlight(AnnotationHolder holder, TaraIdentifierReference identifierReference) {
		if (identifierReference != null && ReferenceManager.resolve(identifierReference) != null) {
			TextAttributesKey component = createTextAttributesKey("CONCEPT_REFERENCE",
				new TextAttributes(null, null, null, null, Font.ITALIC));
			holder.createInfoAnnotation(identifierReference, "reference").setTextAttributes(component);
		}
	}

}
