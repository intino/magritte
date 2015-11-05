package tara.intellij.annotator.imports;

import com.intellij.codeInsight.intention.HighPriorityAction;
import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.TaraModel;
import tara.lang.model.Node;

import java.io.File;

public class ImportQuickFix implements LocalQuickFix, HighPriorityAction {

	private final String anImport;
	private final TaraModel file;

	public ImportQuickFix(TaraModel fileDestiny, Node nodeToImport) {
		this.file = fileDestiny;
		String fileName = new File(nodeToImport.file()).getName();
		anImport = fileName.substring(0, fileName.lastIndexOf('.'));
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