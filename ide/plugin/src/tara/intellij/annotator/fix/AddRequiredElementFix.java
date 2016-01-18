package tara.intellij.annotator.fix;

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
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.codeinsight.livetemplates.TaraTemplateContext;
import tara.intellij.lang.psi.TaraNode;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.lang.model.Node;
import tara.lang.semantics.Constraint;

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
			filter(constraint -> constraint instanceof Constraint.Component && ((Constraint.Component) constraint).compositionRule().isRequired()).
			map(constraint -> (Constraint.Component) constraint).collect(Collectors.toList());
		filterPresentElements(requires);
		createLiveTemplateFor(requires, file, editor);
		PsiDocumentManager.getInstance(file.getProject()).doPostponedOperationsAndUnblockDocument(editor.getDocument());
	}

	public List<Constraint> findConstraints() {
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
		final Editor componentEditor = positionCursor(file.getProject(), file, getLastComponent());
		TemplateManager.getInstance(file.getProject()).startTemplate(componentEditor, createTemplate(requires, file));
		PsiDocumentManager.getInstance(file.getProject()).doPostponedOperationsAndUnblockDocument(editor.getDocument());
	}

	private TaraNode getLastComponent() {
		final List<Node> components = node.components();
		return (TaraNode) components.get(components.size() - 1);
	}

	public Template createTemplate(List<Constraint.Component> requires, PsiFile file) {
		final Template template = TemplateManager.getInstance(file.getProject()).createTemplate("var", "Tara", createTemplateText(requires));
		addComponents(template, requires);
		((TemplateImpl) template).getTemplateContext().setEnabled(contextType(TaraTemplateContext.class), true);
		return template;
	}

	public void addComponents(Template template, List<Constraint.Component> requires) {
		for (int i = 0; i < requires.size(); i++) template.addVariable("VALUE" + i, "", "", true);
	}

	public String createTemplateText(List<Constraint.Component> requires) {
		String text = "";
		for (int i = 0; i < requires.size(); i++)
			text += "\n" + shortType(requires, i) + " $VALUE" + i + "$";
		return text;
	}

	public String shortType(List<Constraint.Component> requires, int i) {
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
