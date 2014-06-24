package siani.tara.intellij.annotator.imports;

import com.intellij.codeInsight.intention.HighPriorityAction;
import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.project.Project;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraFile;
import org.jetbrains.annotations.NotNull;

public class ImportQuickFix implements LocalQuickFix, HighPriorityAction {

	private final String anImport;
	private final TaraFile file;

	public ImportQuickFix(TaraFile file, Concept conceptToImport) {
		this.file = file;
		String packet = ((TaraFile) conceptToImport.getContainingFile()).getBoxReference().getHeaderReference().getText();
		anImport = packet + "." + conceptToImport.getName();
	}

	@NotNull
	public String getText() {
		return "Import '" + anImport + "'";
	}

	@NotNull
	public String getName() {
		return getText();
	}

	@NotNull
	public String getFamilyName() {
		return "Import";
	}


	public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
		file.addImport(anImport);
	}


}