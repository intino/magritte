package monet.::projectName::.intellij.annotator.imports;

import com.intellij.codeInsight.intention.HighPriorityAction;
import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.project.Project;
import monet.::projectName::.intellij.lang.psi.Definition;
import monet.::projectName::.intellij.lang.psi.::projectProperName::File;
import org.jetbrains.annotations.NotNull;

public class ImportQuickFix implements LocalQuickFix, HighPriorityAction {

	private final String anImport;
	private final ::projectProperName::File file;

	public ImportQuickFix(::projectProperName::File file, Definition definitionToImport) {
		this.file = file;
		String packet = ((::projectProperName::File) definitionToImport.getContainingFile()).getPackage().getHeaderReference().getText();
		anImport = packet + "." + definitionToImport.getName();
	}

	\@NotNull
	public String getText() {
		return "Import '" + anImport + "'";
	}

	\@NotNull
	public String getName() {
		return getText();
	}

	\@NotNull
	public String getFamilyName() {
		return "Import";
	}


	public void applyFix(\@NotNull Project project, \@NotNull ProblemDescriptor descriptor) {
		file.addImport(anImport);
	}


}