package tara.intellij.codeinsight.intentions;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.lang.model.NodeContainer;
import tara.lang.model.Parameter;
import tara.lang.semantics.Constraint;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ToBodyParameters extends ParametersIntentionAction {
	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		final List<Constraint> allowsOf = TaraUtil.getConstraintsOf(TaraPsiImplUtil.getContainerNodeOf(element));
		if (allowsOf == null) return;
		Parameters parameters = getParametersScope(element);
		Map<String, String> parametersData = extractParametersData(parameters.getParameters());
		TaraParameter parameter = TaraPsiImplUtil.getContainerByType(element, TaraParameter.class);
		if (parameter == null || parameter.name() == null) return;
		NodeContainer container = TaraPsiImplUtil.getContainerByType(parameter, NodeContainer.class);
		if (container == null) return;
		PsiDocumentManager.getInstance(project).doPostponedOperationsAndUnblockDocument(editor.getDocument());
		parametersData.remove(parameter.name());
		final TaraElementFactory factory = TaraElementFactory.getInstance(project);
		final TaraVarInit varInit = (TaraVarInit) factory.createVarInit(parameter.name(), parameter.getValue().getText());
		if (varInit == null) return;
		final boolean body = hasBody(container);
		if (body) addNewLine((PsiElement) container);
		else addNewLineIndent((PsiElement) container);
		((PsiElement) container).add(varInit.copy());
		if (parametersData.isEmpty()) parameters.delete();
		else parameters.replace(factory.createExplicitParameters(parametersData));
		PsiDocumentManager.getInstance(project).commitDocument(editor.getDocument());
	}

	private Map<String, String> extractParametersData(List<Parameter> parameters) {
		Map<String, String> map = new LinkedHashMap<>();
		for (Parameter parameter : parameters)
			map.put(parameter.name(), ((Valued) parameter).getValue().getText());
		return map;
	}

	private boolean hasBody(NodeContainer container) {
		return container instanceof TaraNode && ((TaraNode) container).getBody() != null;
	}

	private PsiElement addNewLine(PsiElement node) {
		return node.add(TaraElementFactory.getInstance(node.getProject()).createBodyNewLine(TaraPsiImplUtil.getIndentation(node) + 1));
	}

	private PsiElement addNewLineIndent(PsiElement container) {
		return container.add(TaraElementFactory.getInstance(container.getProject()).createNewLineIndent(TaraPsiImplUtil.getIndentation(container) + 1));
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		Parameters parametersScope = getParametersScope(element);
		return element.isWritable() && parametersScope != null && !parametersScope.getParameters().isEmpty();
	}

	@NotNull
	public String getText() {
		return "Move to body";
	}
}
