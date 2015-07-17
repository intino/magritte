package tara.intellij.annotator;

import com.intellij.lang.LanguageNamesValidation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.refactoring.NamesValidator;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tara.intellij.MessageProvider;
import tara.intellij.lang.TaraLanguage;
import tara.intellij.lang.psi.Identifier;

public class NameIdentifierValidator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof Identifier) {
			final NamesValidator namesValidator = LanguageNamesValidation.INSTANCE.forLanguage(TaraLanguage.INSTANCE);
			if (namesValidator != null && namesValidator.isKeyword(element.getText(), element.getProject()))
				holder.createErrorAnnotation(element, MessageProvider.message("invalid.name"));
		}

	}
}
