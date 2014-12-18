package siani.tara.intellij.annotator.imports;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.TaraBoxFile;
import siani.tara.intellij.lang.psi.TaraElementFactory;

public class CreateConceptQuickFix implements LocalQuickFix {
	private final String name;
	private final String type;
	private final TaraBoxFile file;

	public CreateConceptQuickFix(String name, String type, TaraBoxFile file) {
		this.name = name;
		this.type = type;
		this.file = file;
	}

	@NotNull
	@Override
	public String getName() {
		return "Create Concept " + name;
	}

	@NotNull
	@Override
	public String getFamilyName() {
		return "Create Concept";
	}

	@Override
	public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
		file.add(TaraElementFactory.getInstance(project).createNewLine());
		file.add(TaraElementFactory.getInstance(project).createNewLine());
		file.add(TaraElementFactory.getInstance(project).createNewLine());
		file.addConcept(TaraElementFactory.getInstance(project).createConcept(name, type));
	}
}
