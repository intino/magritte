package monet.tara.model.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFileFactory;
import monet.tara.metamodel.file.TaraFile;
import monet.tara.metamodel.file.TaraFileType;
import monet.tara.metamodelplugin.psi.TaraConceptDefinition;


public class TaraElementFactory {
	public static TaraConceptDefinition createCpncept(Project project, String name) {
		final TaraFile file = createFile(project, name);
		return (TaraConceptDefinition) file.getFirstChild();
	}

	public static TaraFile createFile(Project project, String text) {
		String name = "dummy.m2";
		return (TaraFile) PsiFileFactory.getInstance(project).createFileFromText(name, TaraFileType.INSTANCE, text);
	}
}