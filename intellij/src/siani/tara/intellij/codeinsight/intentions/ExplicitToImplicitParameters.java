package siani.tara.intellij.codeinsight.intentions;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.lang.FacetTarget;
import siani.tara.lang.Node;
import siani.tara.lang.Variable;

import java.util.ArrayList;
import java.util.List;

public class ExplicitToImplicitParameters extends PsiElementBaseIntentionAction implements IntentionAction {
	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		Node node = TaraUtil.getMetaConcept(TaraPsiImplUtil.getConceptContainerOf(element));
		if (node == null) return;
		Parameters explicit = (Parameters) element.getParent();
		List<String> implicit = extractParametersData(node, explicit);
		if (implicit.size() != explicit.getParameters().length) return;
		explicit.replace(TaraElementFactory.getInstance(project).createParameters(implicit.toArray(new String[implicit.size()])));
	}

	private List<String> extractParametersData(Node node, Parameters parameters) {
		List<String> parameterValues = new ArrayList<>();
		List<Variable> variables = getVariables(node, parameters.getParameters()[0]);
		for (Variable variable : variables) {
			Parameter correspondantParameter = getCorrespondantParameter(parameters, variable.getName());
			if (correspondantParameter == null) continue;
			parameterValues.add(correspondantParameter.getValue().getText());
		}
		return parameterValues;
	}

	private List<Variable> getVariables(Node node, Parameter parameter) {
		TaraFacetApply inFacet = parameter.isInFacet();
		List<Variable> facetVariables = null;
		if (inFacet != null && (facetVariables = getAllowedFacet(node, inFacet.getFirstChild().getText(), getContextNameOf(inFacet))) == null)
			return null;
		return (inFacet != null) ? facetVariables : node.getObject().getVariables();
	}

	private Parameter getCorrespondantParameter(Parameters parameters, String name) {
		for (Parameter parameter : parameters.getParameters())
			if (name.equals(parameter.getExplicitName())) return parameter;
		return null;
	}

	private String getContextNameOf(TaraFacetApply inFacet) {
		PsiElement contextOf = TaraPsiImplUtil.getContextOf(inFacet);
		if (contextOf instanceof TaraFacetApply)
			return contextOf.getFirstChild().getText();
		if (contextOf instanceof Concept) return ((Concept) contextOf).getType();
		return null;
	}

	private List<Variable> getAllowedFacet(Node node, String name, String context) {
		FacetTarget target = node.getObject().getAllowedFacetByContext(name, context);
		return target != null ? target.getVariables() : null;
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		return element.isWritable() && element.getParent() instanceof Parameters && ((Parameters) element.getParent()).areExplicit();
	}

	@Override
	public boolean startInWriteAction() {
		return true;
	}

	@NotNull
	@Override
	public String getFamilyName() {
		return getText();
	}

	@NotNull
	public String getText() {
		return "To Implicit parameters";
	}
}
