package siani.tara.intellij.annotator.fix;

import com.intellij.codeInsight.FileModificationService;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.Parameters;
import siani.tara.intellij.lang.psi.TaraElementFactory;
import siani.tara.intellij.lang.psi.TaraFacetApply;
import siani.tara.lang.Variable;

import java.util.ArrayList;
import java.util.List;

public class OfferCompletingFacetParameters implements IntentionAction {
	private final TaraFacetApply facetApply;
	private final List<Variable> variables;

	public OfferCompletingFacetParameters(TaraFacetApply element, List<Variable> variables) {
		this.facetApply = element;
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
		return file.isValid() && facetApply.isValid() && facetApply.getManager().isInProject(facetApply);
	}

	@Override
	public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
		if (!FileModificationService.getInstance().prepareFileForWrite(file)) return;
		Parameters parameters = TaraElementFactory.getInstance(project).createParameters(getNames(variables));
		if (facetApply.getParameters() == null) facetApply.add(parameters);
		else facetApply.getParameters().replace(parameters);
		editor.getCaretModel().moveToOffset(offset());
	}

	private int offset() {
		return facetApply.getParameters().getParameters()[0].getTextOffset() + variables.get(0).getName().length() + 3;
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
