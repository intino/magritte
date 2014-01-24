package monet.::projectName::.intellij.psi;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import monet.::projectName::.intellij.metamodel.file.::projectProperName::File;

public abstract class ::projectProperName::ElementFactory {

	public static ::projectProperName::ElementFactory getInstance(Project project) {
		return ServiceManager.getService(project, ::projectProperName::ElementFactory.class);
	}

	public abstract ::projectProperName::Definition createDefinition(String name);

	public abstract ::projectProperName::File createDummyFile(String text);

	public abstract ::projectProperName::Identifier createNameIdentifier(String name);
}