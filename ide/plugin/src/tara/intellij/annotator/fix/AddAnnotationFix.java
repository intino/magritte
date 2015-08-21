package tara.intellij.annotator.fix;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.Annotations;
import tara.intellij.lang.psi.TaraElementFactory;
import tara.intellij.lang.psi.TaraNode;
import tara.language.model.Node;
import tara.language.model.Tag;

public class AddAnnotationFix implements IntentionAction {
	private final Node node;
	private final Tag tags;

	public AddAnnotationFix(Node node, Tag tags) {
		this.node = node;
		this.tags = tags;
	}

	@NotNull
	@Override
	public String getText() {
		return "Add " + tags.name().toLowerCase() + " annotation";
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
				final TaraNode taraNode = (TaraNode) AddAnnotationFix.this.node;
				Annotations taraAnnotations = taraNode.getSignature().getAnnotations();
				TaraElementFactory factory = TaraElementFactory.getInstance(taraNode.getProject());
				if (taraAnnotations != null) {
					taraAnnotations.getAnnotationList().add(factory.createAnnotation(tags.name().toLowerCase()));
				} else
					taraNode.addAfter((PsiElement) factory.createAnnotations(tags.name().toLowerCase()), taraNode.getSignature());
			}
		};
		action.execute();
	}

	@Override
	public boolean startInWriteAction() {
		return true;
	}
}
