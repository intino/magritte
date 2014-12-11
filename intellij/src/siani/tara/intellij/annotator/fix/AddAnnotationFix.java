package siani.tara.intellij.annotator.fix;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraAnnotationsAndFacets;
import siani.tara.intellij.lang.psi.TaraConcept;
import siani.tara.intellij.lang.psi.TaraElementFactory;
import siani.tara.lang.Annotation;

public class AddAnnotationFix implements IntentionAction {
	private final Concept concept;
	private final Annotation.Annotation annotation;

	public AddAnnotationFix(Concept concept, Annotation.Annotation annotation) {
		this.concept = concept;
		this.annotation = annotation;
	}

	@NotNull
	@Override
	public String getText() {
		return "Add " + annotation.getName() + " annotation";
	}

	@NotNull
	@Override
	public String getFamilyName() {
		return getText();
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
		return file.isValid();
	}

	@Override
	public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
		WriteCommandAction action = new WriteCommandAction(project, file) {
			@Override
			protected void run(@NotNull Result result) throws Throwable {
				TaraAnnotationsAndFacets annotationsAndFacets = ((TaraConcept) concept).getAnnotationsAndFacets();
				if (annotationsAndFacets != null)
					annotationsAndFacets.getAnnotations().add(TaraElementFactory.getInstance(concept.getProject()).createAnnotation(annotation.getName()));
				else {
					TaraAnnotationsAndFacets element =
						TaraElementFactory.getInstance(concept.getProject()).createAnnotationAndFacetWith(annotation.getName());
					concept.addAfter(element, concept.getSignature());
				}
			}
		};
		action.execute();
	}

	@Override
	public boolean startInWriteAction() {
		return true;
	}
}
