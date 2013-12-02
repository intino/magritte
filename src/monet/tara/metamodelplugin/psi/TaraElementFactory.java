package monet.tara.metamodelplugin.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFileFactory;
import monet.tara.metamodelplugin.file.TaraFile;
import monet.tara.metamodelplugin.file.TaraFileType;


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