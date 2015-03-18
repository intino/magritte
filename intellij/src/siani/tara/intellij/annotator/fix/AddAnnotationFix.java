package siani.tara.intellij.annotator.fix;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.lexer.Annotation;

public class AddAnnotationFix implements IntentionAction {
	private final Node node;
	private final Annotation annotation;

	public AddAnnotationFix(Node node, Annotation annotation) {
		this.node = node;
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
				Annotations taraAnnotations = node.getAnnotationsNode();
				TaraElementFactory factory = TaraElementFactory.getInstance(node.getProject());
				if (taraAnnotations != null) {
					taraAnnotations.getAnnotationList().add(factory.createAnnotation(annotation.getName()));
				}
				else node.addAfter(factory.createAnnotations(annotation.getName()), node.getSignature());
			}
		};
		action.execute();
	}

	@Override
	public boolean startInWriteAction() {
		return true;
	}
}
