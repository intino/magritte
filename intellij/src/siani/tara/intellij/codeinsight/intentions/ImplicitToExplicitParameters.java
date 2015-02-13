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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ImplicitToExplicitParameters extends PsiElementBaseIntentionAction implements IntentionAction {
	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		Node node = TaraUtil.getMetaConcept(TaraPsiImplUtil.getConceptContainerOf(element));
		if (node == null) return;
		Parameters implicit = (Parameters) element.getParent();
		Map<String, String> explicit = extractParametersData(implicit, node);
		if (explicit.size() != implicit.getParameters().length) return;
		implicit.replace(TaraElementFactory.getInstance(project).createExplicitParameters(explicit));
	}

	private Map<String, String> extractParametersData(Parameters parameters, Node node) {
		Map<String, String> map = new LinkedHashMap<>();
		for (Parameter parameter : parameters.getParameters()) {
			Variable variable = findVariable(node, parameter);
			if (variable == null) continue;
			map.put(variable.getName(), parameter.getValue().getText());
		}
		return map;
	}

	private Variable findVariable(Node node, Parameter parameter) {
		TaraFacetApply inFacet = parameter.isInFacet();
		List<Variable> facetVariables = null;
		if (inFacet != null && (facetVariables = getAllowedFacet(node, inFacet.getFirstChild().getText(), getContextNameOf(inFacet))) == null)
			return null;
		List<Variable> variables = (inFacet != null) ? facetVariables : node.getObject().getVariables();
		int indexInParent = parameter.getIndexInParent();
		if (indexInParent >= variables.size()) return null;
		return variables.get(indexInParent);
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
		return element.isWritable() && (element.getParent() instanceof Parameters) && !((Parameters) element.getParent()).areExplicit();
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
		return "To Explicit parameters";
	}
}
