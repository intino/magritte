package monet.::projectName::.intellij.annotator;

import com.intellij.codeInsight.FileModificationService;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import monet.::projectName::.intellij.lang.psi.Parameters;
import monet.::projectName::.intellij.lang.psi.Signature;
import monet.::projectName::.intellij.lang.psi.::projectProperName::ElementFactory;
import monet.tara.lang.NodeAttribute;
import monet.tara.lang.Variable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AddParametersFix implements IntentionAction {

	Signature signature;
	private final List<Variable> variables;


	public AddParametersFix(Signature signature, List<Variable> variables) {
		this.signature = signature;
		this.variables = variables;
	}

	\@NotNull
	\@Override
	public String getText() {
		return "Add parameters";
	}

	\@NotNull
	\@Override
	public String getFamilyName() {
		return "parameters fix";
	}

	\@Override
	public boolean isAvailable(\@NotNull Project project, Editor editor, PsiFile file) {
		return file.isValid() && signature.isValid() && signature.getManager().isInProject(signature);
	}

	\@Override
	public void invoke(\@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
		if (!FileModificationService.getInstance().prepareFileForWrite(file)) return;
		boolean stringAttribute = isStringAttribute(variables.get(0));
		Parameters parameters = ::projectProperName::ElementFactory.getInstance(project).createParameters(stringAttribute);
		signature.add(parameters);
		editor.getCaretModel().moveToOffset(signature.getParameters().getTextOffset() + 1 + (stringAttribute ? 1 \: 0));
	}

	\@Override
	public boolean startInWriteAction() {
		return true;
	}

	public boolean isStringAttribute(Variable variable) {
		if (variable instanceof NodeAttribute) {
			NodeAttribute o = (NodeAttribute) variable;
			if ("string".equals(o.getPrimitiveType().toLowerCase()) || "alias".equals(o.getPrimitiveType().toLowerCase()))
				return true;
		}
		return false;
	}
}
