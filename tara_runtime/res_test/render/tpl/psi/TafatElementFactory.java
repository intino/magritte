package monet.tafat.intellij.psi;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import monet.tafat.intellij.metamodel.file.TafatFile;

public abstract class TafatElementFactory {

	public static TafatElementFactory getInstance(Project project) {
		return ServiceManager.getService(project, TafatElementFactory.class);
	}

	public abstract TafatDefinition createDefinition(String name);

	public abstract TafatFile createDummyFile(String text);

	public abstract TafatIdentifier createNameIdentifier(String name);
}