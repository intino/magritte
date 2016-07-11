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
import tara.intellij.lang.psi.TaraElementFactory;
import tara.intellij.lang.psi.TaraFacetApply;
import tara.intellij.lang.psi.TaraNode;
import tara.intellij.lang.psi.TaraRuleContainer;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.lang.model.Facet;
import tara.lang.model.Node;
import tara.lang.model.Parameter;
import tara.lang.model.Parametrized;
import tara.lang.semantics.Constraint;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static tara.lang.model.Primitive.*;

class AddRequiredParameterFix extends WithLiveTemplateFix implements IntentionAction {

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
		List<Constraint.Parameter> requires = findConstraints().stream().
			filter(constraint -> constraint instanceof Constraint.Parameter && ((Constraint.Parameter) constraint).size().isRequired()).
			map(constraint -> (Constraint.Parameter) constraint).collect(Collectors.toList());
		filterPresentParameters(requires);
//		cleanSignature(findAnchor(requires));
		createLiveTemplateFor(requires, file, editor);
		PsiDocumentManager.getInstance(file.getProject()).doPostponedOperationsAndUnblockDocument(editor.getDocument());
	}

	private List<Constraint> findConstraints() {
		final Node node = (Node) this.parametrized;
		final List<Constraint> constraintsOf = new ArrayList<>(TaraUtil.getConstraintsOf(node));
		List<Constraint> facetConstraints = new ArrayList<>();
		final List<String> facets = facetTypes(node);
		constraintsOf.stream().
			filter(c -> c instanceof Constraint.Facet && facets.contains(((Constraint.Facet) c).type())).
			forEach(c -> facetConstraints.addAll(((Constraint.Facet) c).constraints()));
		constraintsOf.addAll(facetConstraints);
		return constraintsOf;
	}

//	private void cleanSignature(PsiElement anchor) {
//		if (hasParameters(anchor)) return;
//		final TaraFacetApply facet = TaraPsiImplUtil.getContainerByType(anchor, TaraFacetApply.class);
//		final TaraNode node = TaraPsiImplUtil.getContainerByType(anchor, TaraNode.class);
//		if (facet != null && facet.getParameters() != null) facet.getParameters().delete();
//		else if (node.getSignature().getParameters() != null) ((TaraNode) parametrized).getSignature().getParameters().delete();
//
//	}

	private List<String> facetTypes(Node node) {
		return node.facets().stream().map(Facet::type).collect(Collectors.toList());
	}

	private void createLiveTemplateFor(List<Constraint.Parameter> requires, PsiFile file, Editor editor) {
		if (!FileModificationService.getInstance().prepareFileForWrite(file)) return;
		IdeDocumentHistory.getInstance(file.getProject()).includeCurrentPlaceAsChangePlace();
		PsiDocumentManager.getInstance(file.getProject()).doPostponedOperationsAndUnblockDocument(editor.getDocument());
		final PsiElement anchor = findAnchor(requires);
		final Editor parameterEditor = positionCursor(file.getProject(), file, anchor);
		if (parameterEditor == null) return;
		PsiDocumentManager.getInstance(file.getProject()).doPostponedOperationsAndUnblockDocument(parameterEditor.getDocument());
		TemplateManager.getInstance(file.getProject()).startTemplate(parameterEditor, createTemplate(anchor, requires, file));
		PsiDocumentManager.getInstance(file.getProject()).doPostponedOperationsAndUnblockDocument(editor.getDocument());
		PsiDocumentManager.getInstance(file.getProject()).doPostponedOperationsAndUnblockDocument(parameterEditor.getDocument());
	}

	private PsiElement findAnchor(List<Constraint.Parameter> requires) {
		return requires.get(0).facet().isEmpty() ? findAnchor((TaraNode) parametrized) : findAnchor((TaraFacetApply) ((Node) parametrized).facets().stream().filter(f -> f.type().equals(requires.get(0).facet())).findFirst().get());
	}

	private PsiElement findAnchor(TaraNode node) {
		if (!hasParameters(node)) {
			final PsiElement emptyParameters = TaraElementFactory.getInstance(node.getProject()).createEmptyParameters();
			return node.getSignature().addAfter(emptyParameters, anchor(node)).getFirstChild();
		} else {
			final List<Parameter> parameters = node.getSignature().getParameters().getParameters();
			return (PsiElement) parameters.get(parameters.size() - 1);
		}
	}

	private PsiElement anchor(TaraNode node) {
		return node.getSignature().getMetaIdentifier() != null && node.getSignature().getMetaIdentifier().getNextSibling() instanceof TaraRuleContainer ?
			node.getSignature().getMetaIdentifier().getNextSibling() :
			node.getSignature().getMetaIdentifier();
	}

	private PsiElement findAnchor(TaraFacetApply apply) {
		if (!hasParameters(apply)) {
			final PsiElement emptyParameters = TaraElementFactory.getInstance(apply.getProject()).createEmptyParameters();
			return apply.addAfter(emptyParameters, apply.getMetaIdentifier()).getFirstChild();
		} else {
			final List<Parameter> parameters = apply.getParameters().getParameters();
			return (PsiElement) parameters.get(parameters.size() - 1);
		}
	}

	private boolean hasParameters(PsiElement element) {
		final TaraFacetApply facet = TaraPsiImplUtil.getContainerByType(element, TaraFacetApply.class);
		return facet != null ? hasParameters(facet) : hasParameters(TaraPsiImplUtil.getContainerByType(element, TaraNode.class));
	}

	private boolean hasParameters(TaraFacetApply apply) {
		return apply.getParameters() != null && !apply.getParameters().getParameters().isEmpty();
	}

	private boolean hasParameters(TaraNode node) {
		return node.getSignature().getParameters() != null && !node.getSignature().getParameters().getParameters().isEmpty();
	}

	private Template createTemplate(PsiElement anchor, List<Constraint.Parameter> requires, PsiFile file) {
		final Template template = TemplateManager.getInstance(file.getProject()).createTemplate("var", "Tara", createTemplateText(anchor, requires));
		addVariables(template, requires);
		((TemplateImpl) template).getTemplateContext().setEnabled(contextType(TaraTemplateContext.class), true);
		return template;
	}

	private void addVariables(Template template, List<Constraint.Parameter> requires) {
		for (int i = 0; i < requires.size(); i++)
			template.addVariable("VALUE" + i, "", '"' + (mustBeQuoted(requires.get(i)) ? "\\\"\\\"" : "") + '"', true);
	}

	private boolean mustBeQuoted(Constraint.Parameter parameter) {
		return DATE.equals(parameter.type()) || STRING.equals(parameter.type()) || TIME.equals(parameter.type()) || RESOURCE.equals(parameter.type());
	}

	private String createTemplateText(PsiElement anchor, List<Constraint.Parameter> requires) {
		String text = "";
		for (int i = 0; i < requires.size(); i++)
			text += ", " + requires.get(i).name() + " = " + "$VALUE" + i + "$";
		return !hasParameters(anchor) && !text.isEmpty() ? text.substring(2) : text;
	}

	private void filterPresentParameters(List<Constraint.Parameter> requires) {
		for (Parameter parameter : parametrized.parameters()) {
			Constraint.Parameter require = findInConstraints(requires, parameter.name());
			if (require != null) requires.remove(require);
		}
	}

	@Nullable
	private Constraint.Parameter findInConstraints(List<Constraint.Parameter> constraints, String name) {
		for (Constraint.Parameter require : constraints) if (require.name().equals(name)) return require;
		return null;
	}

	@Override
	public boolean startInWriteAction() {
		return true;
	}
}
