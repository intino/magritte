package monet.tara.intellij.metamodel.psi;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import monet.tara.intellij.metamodel.psi.impl.TaraFileImpl;

public abstract class TaraElementFactory {

	public static TaraElementFactory getInstance(Project project) {
		return ServiceManager.getService(project, TaraElementFactory.class);
	}

	public abstract IConcept createConcept(String name);

	public abstract TaraFileImpl createDummyFile(String text);

	public abstract TaraIdentifier createNameIdentifier(String name);

	public abstract IAttribute createAttribute(String name, String type);
}