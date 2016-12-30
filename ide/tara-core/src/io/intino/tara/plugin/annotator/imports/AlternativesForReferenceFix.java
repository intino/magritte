package io.intino.tara.plugin.annotator.imports;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import io.intino.tara.plugin.lang.psi.Identifier;
import io.intino.tara.plugin.lang.psi.TaraModel;
import io.intino.tara.plugin.lang.psi.impl.TaraUtil;
import org.jetbrains.annotations.NotNull;
import io.intino.tara.lang.model.Node;

import java.util.List;
import java.util.stream.Collectors;

public class AlternativesForReferenceFix implements IntentionAction {
	private final Identifier element;

	public AlternativesForReferenceFix(Identifier element) {
		this.element = element;
	}

	@NotNull
	@Override
	public String getText() {
		return "Find alternatives";
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
		final List<Node> candidates = TaraUtil.getAllNodesOfFile((TaraModel) file).stream().
			filter(c -> element.getText().equals(c.name()) && !c.qualifiedName().contains(Node.ANONYMOUS)).
			collect(Collectors.toList());
	}

	@Override
	public boolean startInWriteAction() {
		return true;
	}
}