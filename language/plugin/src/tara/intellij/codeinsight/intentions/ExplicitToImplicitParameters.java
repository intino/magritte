package tara.intellij.codeinsight.intentions;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

public class ExplicitToImplicitParameters extends ParametersIntentionAction {

	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
//		Node node = TaraUtil.getMetaConcept(TaraPsiImplUtil.getContainerNodeOf(element));
//		if (node == null) return;
//		Parameters explicit = (Parameters) getParametersScope(element);
//		List<String> implicit = extractParametersData(explicit, node);
//		if (implicit.size() != explicit.getParameterList().length) return;
//		explicit.replace(TaraElementFactory.getInstance(project).createParameters(implicit.toArray(new String[implicit.size()])));
	}

//	private List<String> extractParametersData(Parameters parameters, Node node) {
//		List<String> parameterValues = new ArrayList<>();
//		List<Variable> getVariables = getVariables(node, parameters.getParameterList()[0]);
//		for (Variable variable : getVariables) {
//			Parameter correspondentParameter = getCorrespondentParameter(parameters, variable.getName());
//			if (correspondentParameter == null) continue;
//			parameterValues.add(correspondentParameter.getValue().getText());
//		}
//		return parameterValues;
//	}
//
//	private List<Variable> getVariables(Node node, Parameter parameter) {
//		TaraFacetApply inFacet = parameter.isInFacet();
//		List<Variable> facetVariables = null;
//		if (inFacet != null && (facetVariables = getAllowedFacet(node, inFacet.getFirstChild().getText(), getContextNameOf(inFacet))) == null)
//			return Collections.EMPTY_LIST;
//		return (inFacet != null) ? facetVariables : node.getObject().getVariables();
//	}
//
//	private Parameter getCorrespondentParameter(Parameters parameters, String name) {
//		for (Parameter parameter : parameters.getParameterList())
//			if (name.equals(parameter.getName())) return parameter;
//		return null;
//	}

	@NotNull
	public String getText() {
		return "To Implicit parameters";
	}
}
