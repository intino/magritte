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

import static tara.intellij.lang.psi.impl.TaraPsiImplUtil.getContainerByType;

public class FromBodyToExplicitParameters extends ParametersIntentionAction {

	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		Parameter varInit = getContainerByType(element, TaraVarInit.class);
		if (varInit == null || parameterExists(varInit) || varInit.name() == null || varInit.values() == null) return;
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
		return body.getStatements().isEmpty();
	}

	private int getPosition(Parameter parameter) {
		final Constraint.Parameter correspondingConstraint = TaraUtil.getConstraint(TaraPsiImplUtil.getContainerNodeOf((PsiElement) parameter), parameter);
		return correspondingConstraint == null ? 0 : correspondingConstraint.position();
	}

	private boolean parameterExists(Parameter varInit) {
		for (Parameter parameter : ((Parametrized) varInit.container()).parameters())
			if (!parameter.equals(varInit) && varInit.name().equals(parameter.name())) return true;
		return false;
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		final TaraVarInit varInit = getContainerByType(element, TaraVarInit.class);
		return element.isWritable() && varInit != null && varInit.getBodyValue() == null;
	}


	@NotNull
	public String getText() {
		return "Move to header";
	}
}
