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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExplicitToImplicitParameters extends ParametersIntentionAction {

	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		final List<Allow> allowsOf = TaraUtil.getAllowsOf(TaraPsiImplUtil.getContainerNodeOf(element));
		if (allowsOf == null) return;
		Parameters parameters = getParametersScope(element);
		List<String> implicit = extractParametersData(parameters.getParameters(), allowsOf);
		if (implicit.size() != parameters.getParameters().size()) return;
		parameters.replace(TaraElementFactory.getInstance(project).createParameters(implicit.toArray(new String[implicit.size()])));
	}

	private List<String> extractParametersData(List<Parameter> parameters, List<Allow> allows) {
		List<String> parameterValues = new ArrayList<>();
		final List<Allow.Parameter> parameterAllows = filterParametersAllow(allows);
		parameterValues.addAll(parameters.stream().
			filter(parameter -> getCorrespondentAllow(parameterAllows, parameter.name()) != null).
			map(parameter -> ((Valued) parameter).getValue().getText()).
			collect(Collectors.toList()));
		return parameterValues;
	}

	private List<Allow.Parameter> filterParametersAllow(List<Allow> allows) {
		return allows.stream().filter(allow -> allow instanceof Allow.Parameter).
			map(allow -> (Allow.Parameter) allow).
			collect(Collectors.toList());
	}

	private Allow.Parameter getCorrespondentAllow(List<Allow.Parameter> parameters, String name) {
		for (Allow.Parameter parameter : parameters)
			if (name.equals(parameter.name())) return parameter;
		return null;
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		PsiElement parametersScope = getParametersScope(element);
		return element.isWritable() && parametersScope != null && ((Parameters) parametersScope).areExplicit();
	}

	@NotNull
	public String getText() {
		return "To implicit parameters";
	}
}
