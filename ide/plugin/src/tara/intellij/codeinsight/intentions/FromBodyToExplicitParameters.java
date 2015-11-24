package tara.intellij.codeinsight.intentions;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.Body;
import tara.intellij.lang.psi.TaraFacetApply;
import tara.intellij.lang.psi.TaraNode;
import tara.intellij.lang.psi.TaraVarInit;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.lang.model.NodeContainer;
import tara.lang.model.Parameter;
import tara.lang.model.Parametrized;
import tara.lang.semantics.Constraint;

public class FromBodyToExplicitParameters extends ParametersIntentionAction {

	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		Parameter varInit = getVarInit(element);
		if (varInit == null || parameterExists(varInit)) return;
		final NodeContainer container = varInit.container();
		((Parametrized) container).addParameter(varInit.name(), getPosition(varInit), varInit.metric(), varInit.line(), varInit.column(), varInit.values());
		((PsiElement) varInit).delete();
		removeEmptyBody(container);
	}

	private void removeEmptyBody(NodeContainer container) {
		Body body = (container instanceof TaraNode) ? ((TaraNode) container).getBody() : ((TaraFacetApply) container).getBody();
		if (body != null && isEmpty(body)) body.delete();
	}

	private boolean isEmpty(Body body) {
		return body.getFacetApplyList().isEmpty() && body.getFacetTargetList().isEmpty() && body.getNodeLinks().isEmpty() && body.getNodeList().isEmpty() && body.getVariableList().isEmpty() && body.getVarInitList().isEmpty();
	}

	private int getPosition(Parameter parameter) {
		final Constraint.Parameter correspondingConstraint = TaraUtil.getCorrespondingConstraint(TaraPsiImplUtil.getContainerNodeOf((PsiElement) parameter), parameter);
		return correspondingConstraint == null ? 0 : correspondingConstraint.position();
	}

	private boolean parameterExists(Parameter varInit) {
		for (Parameter parameter : ((Parametrized) varInit.container()).parameters())
			if (!parameter.equals(varInit) && varInit.name().equals(parameter.name())) return true;
		return false;
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		return element.isWritable() && getVarInit(element) != null;
	}

	public TaraVarInit getVarInit(@NotNull PsiElement element) {
		PsiElement parent = element;
		while (parent != null)
			if (parent instanceof TaraVarInit) return (TaraVarInit) parent;
			else parent = parent.getParent();
		return null;
	}

	@NotNull
	public String getText() {
		return "Move to parameters";
	}
}
