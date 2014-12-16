package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.annotator.semanticAnalizers.VariableAnalyzer;
import siani.tara.intellij.lang.psi.TaraVariable;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;

public class VariableAnnotator extends TaraAnnotator {
	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (TaraVariable.class.isInstance(element)) {
			VariableAnalyzer analyzer = new VariableAnalyzer((TaraVariable) element);
			analyzeAndAnnotate(analyzer);
			if (!analyzer.hasErrors() && isReference((TaraVariable) element))
				addReferenceAnnotation((TaraVariable) element);
		}
	}

	private boolean isReference(TaraVariable variable) {
		if (variable.getVariableType() == null) return false;
		return variable.getVariableType().getReferenceAttribute() != null;
	}

	@SuppressWarnings("ConstantConditions")
	private void addReferenceAnnotation(TaraVariable variable) {
		if (ReferenceManager.resolve(variable.getVariableType().getReferenceAttribute().getIdentifierReference()) != null) {
			Annotation reference = holder.createInfoAnnotation(variable.getVariableType().getReferenceAttribute().getIdentifierReference(), "reference");
			reference.setTextAttributes(DefaultLanguageHighlighterColors.STATIC_FIELD);
		}
	}

}