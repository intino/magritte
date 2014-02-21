package monet.tara.intellij.psi;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import monet.tara.compiler.intellij.psi.TaraIdentifier;
import monet.tara.intellij.metamodel.file.TaraFile;

public abstract class TaraElementFactory {

	public static TaraElementFactory getInstance(Project project) {
		return ServiceManager.getService(project, TaraElementFactory.class);
	}

	public abstract IConcept createConcept(String name);

	public abstract TaraFile createDummyFile(String text);

	public abstract TaraIdentifier createNameIdentifier(String name);

	public abstract IAttribute createAttribute(String name, String type);
}