package monet.::projectName::.intellij.lang.psi;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import monet.::projectName::.intellij.lang.psi.impl.::projectProperName::FileImpl;

public abstract class ::projectProperName::ElementFactory {

	public static ::projectProperName::ElementFactory getInstance(Project project) {
		return ServiceManager.getService(project, ::projectProperName::ElementFactory.class);
	}

	public abstract Definition createDefinition(String name);

	public abstract ::projectProperName::FileImpl createDummyFile(String text);

	public abstract Identifier createNameIdentifier(String name);

	public abstract Attribute createAttribute(String name, String type);

	public abstract Import createImport(String reference);

	public abstract ::projectProperName::Packet createPackage(String reference);

	public abstract PsiElement createNewLine();

	public abstract Parameters createParameters(boolean string);

	public abstract Attribute createWord(String name, String[] types);

	public abstract Attribute createResource(String name, String types);
}