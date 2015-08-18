package tara.intellij.codeinsight.intentions;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.Parameters;
import tara.intellij.lang.psi.TaraElementFactory;
import tara.intellij.lang.psi.Valued;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.language.model.Parameter;
import tara.language.semantics.Allow;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ImplicitToExplicitParameters extends ParametersIntentionAction {
	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		final List<Allow> allowsOf = TaraUtil.getAllowsOf(TaraPsiImplUtil.getContainerNodeOf(element));
		if (allowsOf == null) return;
		Parameters parameters = getParametersScope(element);
		Map<String, String> explicit = extractParametersData(parameters.getParameters(), allowsOf);
		if (explicit.size() != parameters.getParameters().size()) return;
		parameters.replace(TaraElementFactory.getInstance(project).createExplicitParameters(explicit));
	}

	private Map<String, String> extractParametersData(List<Parameter> parameters, List<Allow> allows) {
		Map<String, String> map = new LinkedHashMap<>();
		final List<Allow.Parameter> parameterAllows = filterParametersAllow(allows);
		for (Parameter parameter : parameters) {
			Allow.Parameter variable = findCorrespondingAllow(parameterAllows, parameter.position());
			if (variable != null) map.put(variable.name(), ((Valued) parameter).getValue().getText());
		}
		return map;
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		PsiElement parametersScope = getParametersScope(element);
		return element.isWritable() && parametersScope != null && !((Parameters) parametersScope).areExplicit();
	}

	@NotNull
	public String getText() {
		return "To explicit parameters";
	}
}
