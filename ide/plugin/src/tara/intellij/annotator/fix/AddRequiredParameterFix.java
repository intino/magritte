package tara.intellij.annotator.fix;

import com.intellij.codeInsight.FileModificationService;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInsight.template.Template;
import com.intellij.codeInsight.template.TemplateManager;
import com.intellij.codeInsight.template.impl.TemplateImpl;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.ex.IdeDocumentHistory;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.codeinsight.livetemplates.TaraTemplateContext;
import tara.intellij.lang.psi.TaraElementFactory;
import tara.intellij.lang.psi.TaraFacetApply;
import tara.intellij.lang.psi.TaraNode;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.lang.model.Facet;
import tara.lang.model.Node;
import tara.lang.model.Parameter;
import tara.lang.model.Parametrized;
import tara.lang.semantics.Constraint;

import java.util.List;
import java.util.stream.Collectors;

import static tara.lang.model.Primitive.*;

public class AddRequiredParameterFix extends WithLiveTemplateFix implements IntentionAction {

	private final Parametrized parametrized;

	public AddRequiredParameterFix(PsiElement element) {
		this.parametrized = element instanceof Node ? (Parametrized) element : (Parametrized) TaraPsiImplUtil.getContainerOf(element);
	}

	@Nls
	@NotNull
	@Override
	public String getText() {
		return "Add required parameters";
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
		List<Constraint.Parameter> requires = findRequires().stream().
			filter(constraint -> constraint instanceof Constraint.Parameter).
			map(constraint -> (Constraint.Parameter) constraint).collect(Collectors.toList());
		filterPresentParameters(requires);
		cleanSignature();
		createLiveTemplateFor(requires, file, editor);
		PsiDocumentManager.getInstance(file.getProject()).doPostponedOperationsAndUnblockDocument(editor.getDocument());
	}

	private void cleanSignature() {
		if (hasParameters()) return;
		if (parametrized instanceof TaraNode && ((TaraNode) parametrized).getSignature().getParameters() != null)
			((TaraNode) parametrized).getSignature().getParameters().delete();
		if (parametrized instanceof TaraFacetApply && ((TaraFacetApply) parametrized).getParameters() != null)
			((TaraFacetApply) parametrized).getParameters().delete();

	}

	public List<Constraint> findRequires() {
		return parametrized instanceof Node ? TaraUtil.getConstraintsOf((Node) parametrized) : TaraUtil.getConstraintsOf((Facet) parametrized);
	}

	private void createLiveTemplateFor(List<Constraint.Parameter> requires, PsiFile file, Editor editor) {
		if (!FileModificationService.getInstance().prepareFileForWrite(file)) return;
		IdeDocumentHistory.getInstance(file.getProject()).includeCurrentPlaceAsChangePlace();
		ApplicationManager.getApplication().runWriteAction(() -> {
			PsiElement anchor = findAnchor();
			PsiDocumentManager.getInstance(file.getProject()).doPostponedOperationsAndUnblockDocument(editor.getDocument());
			final Editor parameterEditor = positionCursor(file.getProject(), file, anchor);
			TemplateManager.getInstance(file.getProject()).startTemplate(parameterEditor, createTemplate(requires, file));
			PsiDocumentManager.getInstance(file.getProject()).doPostponedOperationsAndUnblockDocument(parameterEditor.getDocument());
		});
	}

	private PsiElement findAnchor() {
		if (parametrized instanceof TaraNode)
			return findAnchor((TaraNode) parametrized);
		else return findAnchor((TaraFacetApply) parametrized);
	}

	private PsiElement findAnchor(TaraNode node) {
		if (!hasParameters(node)) {
			final PsiElement emptyParameters = TaraElementFactory.getInstance(node.getProject()).createEmptyParameters();
			return node.getSignature().addAfter(emptyParameters, node.getSignature().getMetaIdentifier()).getFirstChild();
		} else {
			final List<Parameter> parameters = node.getSignature().getParameters().getParameters();
			return (PsiElement) parameters.get(parameters.size() - 1);
		}
	}

	private PsiElement findAnchor(TaraFacetApply apply) {
		if (!hasParameters(apply)) {
			final PsiElement emptyParameters = TaraElementFactory.getInstance(apply.getProject()).createEmptyParameters();
			return apply.addAfter(emptyParameters, apply.getMetaIdentifierList().get(0)).getFirstChild();
		} else {
			final List<Parameter> parameters = apply.getParameters().getParameters();
			return (PsiElement) parameters.get(parameters.size() - 1);
		}
	}

	private boolean hasParameters() {
		return parametrized instanceof TaraNode ? hasParameters((TaraNode) parametrized) : hasParameters((TaraFacetApply) parametrized);
	}

	private boolean hasParameters(TaraFacetApply apply) {
		return apply.getParameters() != null && !apply.getParameters().getParameters().isEmpty();
	}

	private boolean hasParameters(TaraNode node) {
		return node.getSignature().getParameters() != null && !node.getSignature().getParameters().getParameters().isEmpty();
	}

	public Template createTemplate(List<Constraint.Parameter> requires, PsiFile file) {
		final Template template = TemplateManager.getInstance(file.getProject()).createTemplate("var", "Tara", createTemplateText(requires));
		addVariables(template, requires);
		((TemplateImpl) template).getTemplateContext().setEnabled(contextType(TaraTemplateContext.class), true);
		return template;
	}

	public void addVariables(Template template, List<Constraint.Parameter> requires) {
		for (int i = 0; i < requires.size(); i++)
			template.addVariable("VALUE" + i, "", '"' + (mustBeQuoted(requires.get(i)) ? "\\\"\\\"" : "") + '"', true);
	}

	private boolean mustBeQuoted(Constraint.Parameter parameter) {
		return DATE.equals(parameter.type()) || STRING.equals(parameter.type()) || TIME.equals(parameter.type());
	}

	public String createTemplateText(List<Constraint.Parameter> requires) {
		String text = "";
		for (int i = 0; i < requires.size(); i++)
			text += ", " + requires.get(i).name() + " = " + "$VALUE" + i + "$";
		return !hasParameters() ? text.substring(2) : text;
	}

	private void filterPresentParameters(List<Constraint.Parameter> requires) {
		for (Parameter parameter : parametrized.parameters()) {
			Constraint.Parameter require = findInRequires(requires, parameter.name());
			if (require != null) requires.remove(require);
		}
	}

	private Constraint.Parameter findInRequires(List<Constraint.Parameter> constraints, String name) {
		for (Constraint.Parameter require : constraints) if (require.name().equals(name)) return require;
		return null;
	}

	@Override
	public boolean startInWriteAction() {
		return true;
	}
}
