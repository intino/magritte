package monet.::projectName::.intellij.annotator.imports;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import monet.::projectName::.intellij.actions.::projectProperName::Templates;
import monet.::projectName::.intellij.actions.::projectProperName::TemplatesFactory;
import org.jetbrains.annotations.NotNull;

public class CreateDefinitionQuickFix implements LocalQuickFix {
	private final String name;
	private final PsiDirectory packet;

	public CreateDefinitionQuickFix(String name, PsiDirectory packet) {
		this.name = name;
		this.packet = packet;
	}

	\@NotNull
	\@Override
	public String getName() {
		return "Create Definition " + name;
	}

	\@NotNull
	\@Override
	public String getFamilyName() {
		return "Create Definition";
	}

	\@Override
	public void applyFix(\@NotNull Project project, \@NotNull ProblemDescriptor descriptor) {
		String fileName = name + ".m1";
		PsiFile file = ::projectProperName::TemplatesFactory.createFromTemplate(packet, name, fileName, ::projectProperName::Templates.::projectUpperName::_DEFINITION, true);
	}
}
