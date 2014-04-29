package monet.tara.intellij.annotator.imports;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import monet.tara.intellij.actions.TaraTemplates;
import monet.tara.intellij.actions.TaraTemplatesFactory;
import org.jetbrains.annotations.NotNull;

public class CreateConceptQuickFix implements LocalQuickFix {
	private final String name;
	private final PsiDirectory packet;

	public CreateConceptQuickFix(String name, PsiDirectory packet) {
		this.name = name;
		this.packet = packet;
	}

	@NotNull
	@Override
	public String getName() {
		return "Create Concept" + name;
	}

	@NotNull
	@Override
	public String getFamilyName() {
		return "Create Concept";
	}

	@Override
	public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
		String fileName = name + ".m2";
		PsiFile file = TaraTemplatesFactory.createFromTemplate(packet, name, fileName, TaraTemplates.TARA_CONCEPT, true);
	}
}
