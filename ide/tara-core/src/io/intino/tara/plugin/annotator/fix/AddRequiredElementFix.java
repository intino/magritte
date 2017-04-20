package io.intino.tara.plugin.annotator.fix;

import com.intellij.codeInsight.FileModificationService;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInsight.template.Template;
import com.intellij.codeInsight.template.TemplateManager;
import com.intellij.codeInsight.template.impl.TemplateImpl;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.ex.IdeDocumentHistory;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import io.intino.tara.plugin.lang.psi.TaraElementFactory;
import io.intino.tara.plugin.lang.psi.impl.TaraPsiImplUtil;
import io.intino.tara.plugin.lang.psi.impl.TaraUtil;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import io.intino.tara.plugin.codeinsight.livetemplates.TaraTemplateContext;
import io.intino.tara.plugin.lang.psi.TaraNode;
import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.model.rules.Size;
import io.intino.tara.lang.semantics.Constraint;

import java.util.List;
import java.util.stream.Collectors;

public class AddRequiredElementFix extends WithLiveTemplateFix implements IntentionAction {

	private final Node node;

	public AddRequiredElementFix(PsiElement element) {
		this.node = element instanceof Node ? (Node) element : TaraPsiImplUtil.getContainerNodeOf(element);
	}

	@Nls
	@NotNull
	@Override
	public String getText() {
		return "Add required element";
	}

	@Nls
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
		List<Constraint.Component> requires = findConstraints().stream().
			filter(constraint -> constraint instanceof Constraint.Component && isRequired((Constraint.Component) constraint)).
			map(constraint -> (Constraint.Component) constraint).collect(Collectors.toList());
		filterPresentElements(requires);
		createLiveTemplateFor(requires, file, editor);
		PsiDocumentManager.getInstance(file.getProject()).doPostponedOperationsAndUnblockDocument(editor.getDocument());
	}

	private boolean isRequired(Constraint.Component constraint) {
		return constraint.rules().stream().filter(r -> r instanceof Size).allMatch(r -> ((Size) r).isRequired());
	}

	private List<Constraint> findConstraints() {
		return TaraUtil.getConstraintsOf(node);
	}

	private void filterPresentElements(List<Constraint.Component> requires) {
		for (Node node : this.node.components()) {
			Constraint.Component require = findInConstraints(requires, node.type());
			if (require != null) requires.remove(require);
		}
	}

	private void createLiveTemplateFor(List<Constraint.Component> requires, PsiFile file, Editor editor) {
		if (!FileModificationService.getInstance().prepareFileForWrite(file)) return;
		IdeDocumentHistory.getInstance(file.getProject()).includeCurrentPlaceAsChangePlace();
		PsiDocumentManager.getInstance(file.getProject()).doPostponedOperationsAndUnblockDocument(editor.getDocument());
		addNewLine(editor, (TaraNode) node);
		final Editor componentEditor = positionCursorAtBegining(file.getProject(), file, editor.getDocument().getLineNumber(((TaraNode) node).getTextOffset()) + 1);
		TemplateManager.getInstance(file.getProject()).startTemplate(componentEditor, createTemplate(requires, file));
		addNewLine(editor, (TaraNode) node);
		PsiDocumentManager.getInstance(file.getProject()).doPostponedOperationsAndUnblockDocument(editor.getDocument());
	}

	private void addNewLine(Editor editor, TaraNode node) {
		final TaraElementFactory factory = TaraElementFactory.getInstance(node.getProject());
		final PsiElement newLine = factory.createNewLine();
		node.add(newLine.copy());
		node.add(newLine.copy());
		PsiDocumentManager.getInstance(node.getProject()).doPostponedOperationsAndUnblockDocument(editor.getDocument());
	}

	private Template createTemplate(List<Constraint.Component> requires, PsiFile file) {
		final Template template = TemplateManager.getInstance(file.getProject()).createTemplate("var", "Tara", createTemplateText(requires, TaraPsiImplUtil.getIndentation((PsiElement) node) + 1));
		addComponents(template, requires);
		((TemplateImpl) template).getTemplateContext().setEnabled(contextType(TaraTemplateContext.class), true);
		return template;
	}

	private void addComponents(Template template, List<Constraint.Component> requires) {
		for (int i = 0; i < requires.size(); i++) template.addVariable("VALUE" + i, "", "", true);
	}

	private String createTemplateText(List<Constraint.Component> requires, int indents) {
		String text = buildIndentation(indents);
		for (int i = 0; i < requires.size(); i++) text += shortType(requires, i) + " $VALUE" + i + "$\n";
		return text;
	}

	private String buildIndentation(int indents) {
		String indentation = "";
		for (int i = 0; i < indents; i++) indentation += "\t";
		return indentation;
	}

	private String shortType(List<Constraint.Component> requires, int i) {
		final String[] type = requires.get(i).type().split("\\.");
		return type[type.length - 1];
	}

	@Nullable
	private Constraint.Component findInConstraints(List<Constraint.Component> constraints, String type) {
		for (Constraint.Component require : constraints) if (require.type().equals(type)) return require;
		return null;
	}

	@Override
	public boolean startInWriteAction() {
		return true;
	}
}
