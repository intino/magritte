package monet.tara.compiler.intellij.psi;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import monet.tara.compiler.intellij.metamodel.file.TaraFile;

public abstract class TaraElementFactory {

	public static TaraElementFactory getInstance(Project project) {
		return ServiceManager.getService(project, TaraElementFactory.class);
	}

	public abstract TaraConcept createConcept(String name);

	public abstract TaraFile createDummyFile(String text);

	public abstract TaraIdentifier createNameIdentifier(String name);
}