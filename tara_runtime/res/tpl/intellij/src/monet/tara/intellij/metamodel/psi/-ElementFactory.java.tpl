package monet.::projectName::.intellij.metamodel.psi;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::FileImpl;

public abstract class ::projectProperName::ElementFactory {

	public static ::projectProperName::ElementFactory getInstance(Project project) {
		return ServiceManager.getService(project, ::projectProperName::ElementFactory.class);
	}

	public abstract Definition createDefinition(String name);

	public abstract ::projectProperName::FileImpl createDummyFile(String text);

	public abstract Identifier createNameIdentifier(String name);

	public abstract Attribute createAttribute(String name, String type);
}