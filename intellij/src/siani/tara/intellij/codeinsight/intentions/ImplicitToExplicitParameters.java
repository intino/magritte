package siani.tara.intellij.codeinsight.intentions;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.Parameters;

public class ImplicitToExplicitParameters extends ParametersIntentionAction implements IntentionAction {
	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
//		Node node = TaraUtil.getMetaConcept(TaraPsiImplUtil.getContainerNodeOf(element));
//		if (node == null) return;
		Parameters implicit = (Parameters) getParametersScope(element);
//		Map<String, String> explicit = extractParametersData(implicit, node);
//		if (explicit.size() != implicit.getParameters().length) return;
//		implicit.replace(TaraElementFactory.getInstance(project).createExplicitParameters(explicit));
	}

//	private Map<String, String> extractParametersData(Parameters parameters, Node node) {
//		Map<String, String> map = new LinkedHashMap<>();
//		for (Parameter parameter : parameters.getParameters()) {
//			Variable variable = findVariable(node, parameter);
//			if (variable == null) continue;
//			map.put(variable.getName(), parameter.getValue().getText());
//		}
//		return map;
//	}

//	private Variable findVariable(Node node, Parameter parameter) {
////		TaraFacetApply inFacet = parameter.isInFacet();
////		List<Variable> facetVariables = null;
////		if (inFacet != null && (facetVariables = getAllowedFacet(node, inFacet.getFirstChild().getText(), getContextNameOf(inFacet))) == null)
////			return null;
////		List<Variable> variables = (inFacet != null) ? facetVariables : node.getObject().getVariables();
////		int indexInParent = parameter.getIndexInParent();
////		if (indexInParent >= variables.size()) return null;
////		return variables.get(indexInParent);
//	}

	@NotNull
	public String getText() {
		return "To Explicit parameters";
	}
}
