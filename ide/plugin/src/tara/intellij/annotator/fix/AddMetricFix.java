package tara.intellij.annotator.fix;

import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.TaraParameter;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.lang.model.Parameter;

class AddMetricFix extends PsiElementBaseIntentionAction {

	private final Parameter parameter;
	private final String[] parameters;

	public AddMetricFix(PsiElement element, String... parameters) {
		this.parameter = TaraPsiImplUtil.getContainerByType(element, TaraParameter.class);
		this.parameters = parameters;
	}

	public AddMetricFix(PsiElement element) {
		this.parameter = TaraPsiImplUtil.getContainerByType(element, TaraParameter.class);
		this.parameters = new String[0];
	}

	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		if (parameters.length > 0) parameter.metric(parameters[0]);
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		return element.getContainingFile().isValid();
	}

	@Nls
	@NotNull
	@Override
	public String getFamilyName() {
		return getText();
	}

	@NotNull
	@Override
	public String getText() {
		return "Add metric";
	}

}