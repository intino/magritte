package siani.tara.intellij.annotator.imports;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import siani.tara.intellij.actions.TaraTemplates;
import siani.tara.intellij.actions.TaraTemplatesFactory;
import siani.tara.intellij.lang.file.TaraFileType;
import org.jetbrains.annotations.NotNull;

public class CreateConceptQuickFix implements LocalQuickFix {
	private final String name;
	private final String type;
	private final PsiDirectory packet;

	public CreateConceptQuickFix(String name, String type, PsiDirectory packet) {
		this.name = name;
		this.type = type;
		this.packet = packet;
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
		String fileName = name + "." + TaraFileType.INSTANCE.getDefaultExtension();
		PsiFile file = TaraTemplatesFactory.createFromTemplate(packet, name, fileName, TaraTemplates.getTemplate(type.toUpperCase()), true);
	}
}