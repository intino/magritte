package siani.tara.intellij.annotator.imports;

import com.intellij.codeInsight.intention.HighPriorityAction;
import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraModel;

public class ImportQuickFix implements LocalQuickFix, HighPriorityAction {

	private final String anImport;
	private final TaraModel file;

	public ImportQuickFix(TaraModel fileDestiny, Concept conceptToImport) {
		this.file = fileDestiny;
		String name = conceptToImport.getContainingFile().getName();
		anImport = name.substring(0, name.lastIndexOf('.'));
	}

	@NotNull
	public String getText() {
		return "Use '" + anImport + "'";
	}

	@NotNull
	public String getName() {
		return getText();
	}

	@NotNull
	public String getFamilyName() {
		return "Use";
	}


	public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
		file.addImport(anImport);
	}


}