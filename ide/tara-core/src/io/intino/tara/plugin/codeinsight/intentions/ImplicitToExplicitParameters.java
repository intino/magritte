package io.intino.tara.plugin.codeinsight.intentions;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import io.intino.tara.plugin.lang.psi.impl.TaraPsiUtil;
import org.jetbrains.annotations.NotNull;
import io.intino.tara.plugin.lang.psi.Parameters;
import io.intino.tara.plugin.lang.psi.TaraElementFactory;
import io.intino.tara.plugin.lang.psi.Valued;
import io.intino.tara.plugin.lang.psi.impl.TaraUtil;
import io.intino.tara.lang.model.Parameter;
import io.intino.tara.lang.semantics.Constraint;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ImplicitToExplicitParameters extends ParametersIntentionAction {
	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		final List<Constraint> allowsOf = TaraUtil.getConstraintsOf(TaraPsiUtil.getContainerNodeOf(element));
		if (allowsOf == null) return;
		Parameters parameters = getParametersScope(element);
		Map<String, String> explicit = extractParametersData(parameters.getParameters());
		if (explicit.size() != parameters.getParameters().size()) return;
		parameters.replace(TaraElementFactory.getInstance(project).createExplicitParameters(explicit));
	}

	private Map<String, String> extractParametersData(List<Parameter> parameters) {
		Map<String, String> map = new LinkedHashMap<>();
		for (Parameter parameter : parameters)
			map.put(parameter.name(), ((Valued) parameter).getValue().getText());
		return map;
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		Parameters parametersScope = getParametersScope(element);
		return element.isWritable() && parametersScope != null && !parametersScope.getParameters().isEmpty() && !parametersScope.areExplicit();
	}

	@NotNull
	public String getText() {
		return "To explicit parameters";
	}
}
