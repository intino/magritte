package siani.tara.intellij.annotator.imports;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.TaraElementFactory;
import siani.tara.intellij.lang.psi.TaraModel;

public class CreateNodeQuickFix implements LocalQuickFix {
	private final String name;
	private final String type;
	private final TaraModel file;

	public CreateNodeQuickFix(String name, String type, TaraModel file) {
		this.name = name;
		this.type = type;
		this.file = file;
	}

	@NotNull
	@Override
	public String getName() {
		return "Create " + type + " " + name;
	}

	@NotNull
	@Override
	public String getFamilyName() {
		return "Create " + type;
	}

	@Override
	public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
		file.add(TaraElementFactory.getInstance(project).createNewLine());
		file.add(TaraElementFactory.getInstance(project).createNewLine());
		file.add(TaraElementFactory.getInstance(project).createNewLine());
		file.addNode(TaraElementFactory.getInstance(project).createConcept(name, type));
	}
}
