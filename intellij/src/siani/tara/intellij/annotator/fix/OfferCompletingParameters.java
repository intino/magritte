package siani.tara.intellij.annotator.fix;

import com.intellij.codeInsight.FileModificationService;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.Parameters;
import siani.tara.intellij.lang.psi.Signature;
import siani.tara.intellij.lang.psi.TaraElementFactory;
import siani.tara.lang.Variable;

import java.util.ArrayList;
import java.util.List;

public class OfferCompletingParameters implements IntentionAction {
	private final Signature signature;
	private final List<Variable> variables;

	public OfferCompletingParameters(Signature element, List<Variable> variables) {
		this.signature = element;
		this.variables = variables;
	}

	@NotNull
	@Override
	public String getText() {
		return "Add explicit parameters";
	}

	@NotNull
	@Override
	public String getFamilyName() {
		return "parameters fix";
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
		return file.isValid() && signature.isValid() && signature.getManager().isInProject(signature);
	}

	@Override
	public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
		if (!FileModificationService.getInstance().prepareFileForWrite(file)) return;
		Parameters parameters = TaraElementFactory.getInstance(project).createParameters(getNames(variables));
		if (signature.getParameters() == null) signature.add(parameters);
		else signature.getParameters().replace(parameters);
		editor.getCaretModel().moveToOffset(offset());
	}

	private int offset() {
		return signature.getParameters().getParameters()[0].getTextOffset() + variables.get(0).getName().length() + 3;
	}


	private String[] getNames(List<Variable> variables) {
		List<String> names = new ArrayList<>();
		for (Variable variable : variables) names.add(variable.getName());
		return names.toArray(new String[names.size()]);
	}

	@Override
	public boolean startInWriteAction() {
		return true;
	}

}
