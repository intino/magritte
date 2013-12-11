package monet.tara.metamodel;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpressionStatement;
import com.intellij.psi.PsiIdentifier;
import monet.tara.metamodel.psi.impl.TaraUtil;
import monet.tara.metamodelplugin.psi.TaraConceptDefinition;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TaraAnnotator implements Annotator {

	@Override
	public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
		if (element instanceof PsiIdentifier) {
			PsiExpressionStatement identifier;
			PsiIdentifier psiIdentifier = (PsiIdentifier) element;
			if (psiIdentifier != null && psiIdentifier.getText().equals("Tara")) {
				PsiElement taraToken = psiIdentifier.getNextSibling();
				PsiElement conceptIdentifier = taraToken.getNextSibling();
				if (conceptIdentifier != null && taraToken.getText().equals(":"))
					identifier = (PsiExpressionStatement) conceptIdentifier;
				else return;
				Project project = element.getProject();
				List<TaraConceptDefinition> concepts = TaraUtil.findConcept(project, identifier.getText().replace(";", ""));
				if (concepts.size() == 1) {
					TextRange range = new TextRange(element.getTextRange().getStartOffset(), identifier.getTextRange().getEndOffset());
					Annotation annotation = holder.createInfoAnnotation(range, "Concept " + concepts.get(0).getIdentifier());
					annotation.setTextAttributes(DefaultLanguageHighlighterColors.LINE_COMMENT);
				} else if (concepts.size() == 0) {
					TextRange range = new TextRange(element.getTextRange().getStartOffset() + 6,
							                               element.getTextRange().getEndOffset());
					holder.createErrorAnnotation(range, "Unresolved concept");
				}
			}
		}
	}
}