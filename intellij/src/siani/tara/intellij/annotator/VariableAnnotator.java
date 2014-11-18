package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.TaraVariable;

public class VariableAnnotator extends TaraAnnotator {
	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (!TaraVariable.class.isInstance(element)) return;
		TaraVariable variable = (TaraVariable) element;
		if (isReference(variable)) addReferenceAnnotation(variable);
	}

	private boolean isReference(TaraVariable variable) {
		return (variable.getIdentifierReference() != null);
	}

	private void addReferenceAnnotation(TaraVariable variable) {
		Annotation aggregated = holder.createInfoAnnotation(variable.getIdentifierReference(), "reference");
		aggregated.setTextAttributes(createReferenceHighlight());
	}

	private TextAttributesKey createReferenceHighlight() {
		return DefaultLanguageHighlighterColors.STATIC_FIELD;
	}
}
