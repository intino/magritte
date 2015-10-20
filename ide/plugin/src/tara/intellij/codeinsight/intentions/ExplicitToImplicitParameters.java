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

import java.util.*;
import java.util.stream.Collectors;

public class ExplicitToImplicitParameters extends ParametersIntentionAction {

	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		final List<Allow> allowsOf = TaraUtil.getAllowsOf(TaraPsiImplUtil.getContainerNodeOf(element));
		if (allowsOf == null) return;
		Parameters parameters = getParametersScope(element);
		Map<Integer, String> implicit = extractParametersData(parameters.getParameters(), allowsOf);
		if (implicit.size() != parameters.getParameters().size()) return;
		parameters.replace(TaraElementFactory.getInstance(project).createParameters(sort(implicit)));
	}

	private String[] sort(Map<Integer, String> map) {
		List<String> values = new ArrayList<>();
		List<Integer> sortedKeys = new ArrayList(map.keySet());
		Collections.sort(sortedKeys);
		values.addAll(sortedKeys.stream().map(map::get).collect(Collectors.toList()));
		return values.toArray(new String[values.size()]);
	}

	@SuppressWarnings("ConstantConditions")
	private Map<Integer, String> extractParametersData(List<Parameter> parameters, List<Allow> allows) {
		Map<Integer, String> result = new HashMap<>();
		final List<Allow.Parameter> parameterAllows = filterParametersAllow(allows);
		for (Parameter parameter : parameters) {
			final Allow.Parameter allow = findCorrespondingAllow(parameterAllows, parameter.name());
			if (allow != null) result.put(allow.position(), ((Valued) parameter).getValue().getText());
		}
		return result;
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
